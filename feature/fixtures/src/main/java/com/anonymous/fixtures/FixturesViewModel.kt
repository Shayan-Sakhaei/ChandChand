package com.anonymous.fixtures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.domain.model.GetFixturesUseCase
import com.anonymous.fixtures.mapper.FixtureEntityUiMapper
import com.anonymous.fixtures.model.FixturesPerLeagueModel
import com.anonymous.testing.wrapEspressoIdlingResource
import com.anonymous.ui.extension.getDateFromToday
import com.anonymous.ui.model.DAY
import com.anonymous.ui.model.IModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class FixturesViewModel @Inject constructor(
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
                is FixturesIntent.GetFixtures -> getFixtures()
                is FixturesIntent.GetSomedayFixtures -> getSomedayFixtures(it.date)
            }
        }
    }

    private suspend fun updateState(handler: suspend (intent: FixturesState) -> FixturesState) {
        _state.value = handler(state.value)
    }


    private fun getFixtures() {
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                try {
                    updateState { it.copy(isLoading = true) }

                    coroutineScope {
                        val yesterday =
                            async { getFixturesUseCase.execute(getDateFromToday(-1)) }
                        val today =
                            async { getFixturesUseCase.execute(getDateFromToday(0)) }
                        val tomorrow =
                            async { getFixturesUseCase.execute(getDateFromToday(1)) }
                        val dayAfterTomorrow =
                            async { getFixturesUseCase.execute(getDateFromToday(2)) }

                        when (val yesterdayResponse = yesterday.await()) {
                            is com.anonymous.common.result.Result.Success -> {
                                val fixtures = entityUiMapper.map(yesterdayResponse.data)
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        yesterdayFixturesState = DailyFixturesState(fixtures = fixtures)
                                    )
                                }
                            }
                            is com.anonymous.common.result.Result.Error -> {
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        yesterdayFixturesState = DailyFixturesState(errorMessage = "yesterday fixtures failed!")
                                    )
                                }
                            }
                        }

                        when (val todayResponse = today.await()) {
                            is com.anonymous.common.result.Result.Success -> {
                                val fixtures = entityUiMapper.map(todayResponse.data)
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        todayFixturesState = DailyFixturesState(fixtures = fixtures)
                                    )
                                }
                            }
                            is com.anonymous.common.result.Result.Error -> {
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        todayFixturesState = DailyFixturesState(errorMessage = "today fixtures failed!")
                                    )
                                }
                            }
                        }

                        when (val tomorrowResponse = tomorrow.await()) {
                            is com.anonymous.common.result.Result.Success -> {
                                val fixtures = entityUiMapper.map(tomorrowResponse.data)
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        tomorrowFixturesState = DailyFixturesState(fixtures = fixtures)
                                    )
                                }
                            }
                            is com.anonymous.common.result.Result.Error -> {
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        tomorrowFixturesState = DailyFixturesState(errorMessage = "tomorrow fixtures failed!")
                                    )
                                }
                            }
                        }

                        when (val dayAfterTomorrowResponse = dayAfterTomorrow.await()) {
                            is com.anonymous.common.result.Result.Success -> {
                                val fixtures = entityUiMapper.map(dayAfterTomorrowResponse.data)
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        dayAfterTomorrowFixturesState = DailyFixturesState(fixtures = fixtures)
                                    )
                                }
                            }
                            is com.anonymous.common.result.Result.Error -> {
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        dayAfterTomorrowFixturesState = DailyFixturesState(
                                            errorMessage = "dayAfterTomorrow fixtures failed!"
                                        )
                                    )
                                }
                            }
                        }


                    }
                } catch (e: Exception) {
                    updateState { it.copy(isLoading = false, errorMessage = e.message) }
                }
            }
        }
    }

    fun getSomedayFixtures(date: String) {
        viewModelScope.launch {
            when (val response = getFixturesUseCase.execute(date)) {
                is com.anonymous.common.result.Result.Success -> {
                    val fixtures = entityUiMapper.map(response.data)
                    updateState {
                        it.copy(
                            isLoading = false,
                            somedayDate = date,
                            somedayFixturesState = DailyFixturesState(fixtures = fixtures)
                        )
                    }
                }
                is com.anonymous.common.result.Result.Error -> {
                    updateState {
                        it.copy(
                            isLoading = false,
                            somedayFixturesState = DailyFixturesState(errorMessage = "someday fixtures failed!")
                        )
                    }
                }
            }
        }
    }

    fun onLeagueHeaderClick(model: FixturesPerLeagueModel, day: DAY) {
        val oldFixtureList = when (day) {
            DAY.YESTERDAY -> {
                _state.value.yesterdayFixturesState
            }
            DAY.TODAY -> {
                _state.value.todayFixturesState
            }
            DAY.TOMORROW -> {
                _state.value.tomorrowFixturesState
            }
            DAY.DAY_AFTER_TOMORROW -> {
                _state.value.dayAfterTomorrowFixturesState
            }
            DAY.SOMEDAY -> {
                _state.value.somedayFixturesState
            }
        }

        if (oldFixtureList.fixtures.isNotEmpty()) {
            val newFixtureList = oldFixtureList.fixtures.map {
                if (it == model) {
                    it.copy(isExpanded = model.isExpanded.not())
                } else {
                    it
                }
            }
            viewModelScope.launch {
                when (day) {
                    DAY.YESTERDAY -> {
                        updateState { it.copy(yesterdayFixturesState = DailyFixturesState(fixtures = newFixtureList)) }
                    }
                    DAY.TODAY -> {
                        updateState { it.copy(todayFixturesState = DailyFixturesState(fixtures = newFixtureList)) }
                    }
                    DAY.TOMORROW -> {
                        updateState { it.copy(tomorrowFixturesState = DailyFixturesState(fixtures = newFixtureList)) }
                    }
                    DAY.DAY_AFTER_TOMORROW -> {
                        updateState {
                            it.copy(
                                dayAfterTomorrowFixturesState = DailyFixturesState(
                                    fixtures = newFixtureList
                                )
                            )
                        }
                    }
                    DAY.SOMEDAY -> {
                        updateState { it.copy(somedayFixturesState = DailyFixturesState(fixtures = newFixtureList)) }
                    }
                }
            }
        }
    }
}