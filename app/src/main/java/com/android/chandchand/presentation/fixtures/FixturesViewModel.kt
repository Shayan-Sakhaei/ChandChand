package com.android.chandchand.presentation.fixtures

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.usecase.GetFixturesUseCase
import com.android.chandchand.presentation.common.IModel
import com.android.chandchand.presentation.mapper.FixtureEntityUiMapper
import com.android.chandchand.presentation.model.LeagueModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class FixturesViewModel @ViewModelInject constructor(
    private val getFixturesUseCase: GetFixturesUseCase,
    private val entityUiMapper: FixtureEntityUiMapper
) : ViewModel(), IModel<FixturesState, FixturesIntent> {

    override val intents: Channel<FixturesIntent> = Channel(Channel.UNLIMITED)
    private val _state = MutableStateFlow(FixturesState())
    override val state: StateFlow<FixturesState> get() = _state

    init {
        viewModelScope.launch {
            handleIntent()
        }
    }

    private suspend fun handleIntent() {
        intents.consumeAsFlow().collect {
            when (it) {
                is FixturesIntent.GetFixtures -> getFixtures(it.date)
            }
        }
    }

    private suspend fun updateState(handler: suspend (intent: FixturesState) -> FixturesState) {
        _state.value = handler(state.value)
    }


    fun getFixtures(date: String) {
        viewModelScope.launch {
            try {
                updateState { it.copy(isLoading = true) }
                getFixturesUseCase.execute(date)
                    .onStart {}
                    .catch {}
                    .collect { fixtureEntities ->
                        when (fixtureEntities) {
                            is Result.Success -> {
                                val fixtures = entityUiMapper.map(
                                    fixtureEntities.data
                                )
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        fixtures = fixtures
                                    )
                                }

                            }
                            is Result.Error -> {
                                updateState { it.copy(isLoading = false, errorMessage = "failed!") }
                            }
                        }
                    }
            } catch (e: Exception) {
                updateState { it.copy(isLoading = false, errorMessage = e.message) }
            }
        }
    }


    fun onLeagueHeaderTapped(leagueModel: LeagueModel) {
        val oldFixturesPerLeague = _state.value.fixtures
        if (oldFixturesPerLeague.isNotEmpty()) {
            val newLeague = leagueModel.copy(isExpanded = leagueModel.isExpanded.not())
            val newFixtureList = oldFixturesPerLeague.map {
                if (it.leagueModel == leagueModel) {
                    it.copy(leagueModel = newLeague)
                } else {
                    it
                }
            }
            viewModelScope.launch {
                updateState { it.copy(fixtures = newFixtureList) }
            }
        }
    }
}