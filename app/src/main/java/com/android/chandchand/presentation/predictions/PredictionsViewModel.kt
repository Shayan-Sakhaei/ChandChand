package com.android.chandchand.presentation.predictions

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.usecase.GetPredictionsUseCase
import com.android.chandchand.presentation.common.IModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PredictionsViewModel @ViewModelInject constructor(
    private val getPredictionsUseCase: GetPredictionsUseCase
) : ViewModel(), IModel<PredictionsState, PredictionsIntent> {

    override val intents: Channel<PredictionsIntent> = Channel(Channel.UNLIMITED)
    private val _state = MutableStateFlow(PredictionsState())
    override val state: StateFlow<PredictionsState> get() = _state

    fun send(intent: PredictionsIntent) {
        intents.offer(intent)
    }

    init {
        viewModelScope.launch {
            handleIntent()
        }
    }

    private suspend fun handleIntent() {
        intents.consumeAsFlow().collect {
            when (it) {
                is PredictionsIntent.GetPredictions -> getPredictions(it.fixtureId)
            }
        }
    }

    private suspend fun updateState(handler: suspend (intent: PredictionsState) -> PredictionsState) {
        _state.value = handler(state.value)
    }

    private fun getPredictions(fixtureId: Int) {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true, errorMessage = null) }
            getPredictionsUseCase.execute(fixtureId)
                .catch {
                    updateState { it.copy(isLoading = false, errorMessage = "failed") }
                }
                .collect { predictionsEntity ->
                    when (predictionsEntity) {
                        is Result.Success -> {
                            updateState {
                                it.copy(
                                    isLoading = false,
                                    predictions = predictionsEntity.data,
                                    errorMessage = null
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
}