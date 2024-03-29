package com.android.chandchand.presentation.leagues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.chandchand.presentation.common.IModel
import com.android.domain.common.Result
import com.android.domain.usecase.GetStandingsUseCase
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
class LeaguesViewModel @Inject constructor(
    private val getStandingsUseCase: GetStandingsUseCase
) : ViewModel(), IModel<LeaguesState, LeaguesIntent> {


    override val intents: Channel<LeaguesIntent> = Channel(Channel.UNLIMITED)
    private val _state = MutableStateFlow(LeaguesState())
    override val state: StateFlow<LeaguesState> get() = _state

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

    private suspend fun updateState(handler: suspend (intent: LeaguesState) -> LeaguesState) {
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