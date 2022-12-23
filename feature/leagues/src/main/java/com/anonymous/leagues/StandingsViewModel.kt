package com.anonymous.leagues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.common.result.Result
import com.anonymous.domain.model.GetStandingsUseCase
import com.anonymous.ui.model.IModel
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
class StandingsViewModel @Inject constructor(
    private val getStandingsUseCase: GetStandingsUseCase
) : ViewModel(), IModel<StandingsState, LeaguesIntent> {


    override val intents: Channel<LeaguesIntent> = Channel(Channel.UNLIMITED)
    private val _state = MutableStateFlow(StandingsState())
    override val state: StateFlow<StandingsState> get() = _state

    init {
        viewModelScope.launch {
            handleIntent()
        }
    }

    private suspend fun handleIntent() {
        intents.consumeAsFlow().collect {
            when (it) {
                is LeaguesIntent.GetStandings -> getStandings(it.leagueId)
            }
        }
    }

    private suspend fun updateState(handler: suspend (intent: StandingsState) -> StandingsState) {
        _state.value = handler(state.value)
    }

    private fun getStandings(leagueId: Int) {
        viewModelScope.launch {
            when (val response = getStandingsUseCase.execute(leagueId)) {
                is Result.Success -> {
                    updateState {
                        it.copy(
                            isLoading = false,
                            standings = response.data
                        )
                    }
                }
                is Result.Error -> {
                    updateState { it.copy(isLoading = false, errorMessage = "failed!") }

                }
            }
        }
    }
}