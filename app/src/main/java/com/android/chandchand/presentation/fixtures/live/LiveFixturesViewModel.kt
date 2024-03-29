package com.android.chandchand.presentation.fixtures.live

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.chandchand.presentation.common.IModel
import com.android.chandchand.presentation.mapper.LiveFixtureEntityUiMapper
import com.android.chandchand.presentation.model.LiveFixturesPerLeagueModel
import com.android.chandchand.presentation.model.LiveFixturesPerLeagueModels
import com.android.chandchand.wrapEspressoIdlingResource
import com.android.domain.common.Result
import com.android.domain.usecase.GetLiveFixturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class LiveFixturesViewModel @Inject constructor(
    private val getLiveFixturesUseCase: GetLiveFixturesUseCase,
    private val liveEntityUiMapper: LiveFixtureEntityUiMapper
) : ViewModel(), IModel<LiveFixturesState, LiveFixturesIntent> {

    override val intents: Channel<LiveFixturesIntent> = Channel(Channel.UNLIMITED)
    private val _state = MutableStateFlow(LiveFixturesState())
    override val state: StateFlow<LiveFixturesState> get() = _state

    init {
        viewModelScope.launch {
            handleIntent()
        }
    }

    private suspend fun handleIntent() {
        intents.consumeAsFlow().collect {
            when (it) {
                is LiveFixturesIntent.GetLiveFixtures -> getLiveFixtures()
            }
        }
    }

    private suspend fun updateState(handler: suspend (intent: LiveFixturesState) -> LiveFixturesState) {
        _state.value = handler(state.value)
    }

    private fun getLiveFixtures() {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                try {
                    updateState { it.copy(isLoading = true) }
                    when (val liveResponse = getLiveFixturesUseCase.execute()) {
                        is Result.Success -> {
                            val liveFixtures = liveEntityUiMapper.map(
                                liveResponse.data
                            )
                            updateState {
                                it.copy(
                                    isLoading = false,
                                    liveFixtures = liveFixtures
                                )
                            }
                        }
                        is Result.Error -> {
                            updateState {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = "failed!"
                                )
                            }
                        }
                    }

                } catch (e: Exception) {
                    updateState { it.copy(isLoading = false, errorMessage = e.message) }
                }
            }
        }
    }

    fun onLeagueHeaderClick(model: LiveFixturesPerLeagueModel) {
        val oldFixturesList = _state.value.liveFixtures
        if (oldFixturesList.entities.isNotEmpty()) {
            val newFixtureList = oldFixturesList.entities.map {
                if (it == model) {
                    it.copy(isExpanded = model.isExpanded.not())
                } else {
                    it
                }
            }
            viewModelScope.launch {
                updateState {
                    it.copy(
                        liveFixtures = LiveFixturesPerLeagueModels(
                            results = it.liveFixtures.results,
                            entities = newFixtureList
                        )
                    )
                }
            }
        }
    }
}