package com.android.chandchand.presentation.predictions

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.android.chandchand.presentation.common.IModel
import com.android.domain.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PredictionsViewModel @Inject constructor(
    private val getPredictionsUseCase: com.android.domain.usecase.GetPredictionsUseCase
) : ViewModel(), IModel<PredictionsState, PredictionsIntent> {

    override val intents: Channel<PredictionsIntent> = Channel(Channel.UNLIMITED)
    private val _state = MutableStateFlow(PredictionsState())
    override val state: StateFlow<PredictionsState> get() = _state

    fun send(intent: PredictionsIntent) {
        intents.trySend(intent)
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
            when (val response = getPredictionsUseCase.execute(fixtureId)) {
                is Result.Success -> {
                    updateState {
                        it.copy(
                            isLoading = false,
                            predictions = response.data,
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

    fun prepareState(homeTeamLogoUrl: String, awayTeamLogoUrl: String, date: String, time: String) {
        viewModelScope.launch {
            updateState {
                it.copy(
                    homeTeamLogoUrl = homeTeamLogoUrl,
                    awayTeamLogoUrl = awayTeamLogoUrl,
                    date = date,
                    time = time
                )
            }
        }
    }

    fun palette(drawable: Drawable?, homeAway: HomeAway) {
        drawable?.let {
            val builder = Palette.Builder(it.toBitmap())
            builder.generate { palette: Palette? ->
                val dominantRGB: Int? = palette?.dominantSwatch?.rgb
                dominantRGB?.let { rgb ->
                    updateTeamColor(Color(rgb), homeAway)
                }
            }
        }

    }

    private fun updateTeamColor(color: Color, homeAway: HomeAway) =
        viewModelScope.launch {
            when (homeAway) {
                HomeAway.HOME -> {
                    updateState { it.copy(homeTeamColor = color) }
                }
                HomeAway.AWAY -> {
                    updateState { it.copy(awayTeamColor = color) }
                }
            }
        }
}