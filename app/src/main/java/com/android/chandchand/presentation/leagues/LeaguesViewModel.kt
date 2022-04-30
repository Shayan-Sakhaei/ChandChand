package com.android.chandchand.presentation.leagues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.usecase.GetStandingsUseCase
import com.android.chandchand.presentation.common.IModel
import com.android.chandchand.presentation.model.LeagueTitleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
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

    val _selectedLeagueTitleModel = MutableLiveData<LeagueTitleModel>()
    val selectedLeagueTitleModel: LiveData<LeagueTitleModel> get() = _selectedLeagueTitleModel

    private suspend fun updateState(handler: suspend (intent: LeaguesState) -> LeaguesState) {
        _state.value = handler(state.value)
    }

    fun getStandings(leagueId: Int) {
        viewModelScope.launch {
            try {
                updateState { it.copy(isLoading = true, standings = emptyList()) }
                getStandingsUseCase.execute(leagueId)
                    .onStart { }
                    .catch { }
                    .collect { standingEntities ->
                        when (standingEntities) {
                            is Result.Success -> {
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        standings = standingEntities.data
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
}