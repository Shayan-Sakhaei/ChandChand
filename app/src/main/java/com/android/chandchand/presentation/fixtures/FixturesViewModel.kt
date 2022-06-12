package com.android.chandchand.presentation.fixtures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.data.common.Result
import com.android.domain.usecase.GetFixturesUseCase
import com.android.chandchand.presentation.common.IModel
import com.android.chandchand.presentation.mapper.FixtureEntityUiMapper
import com.android.chandchand.presentation.model.FixturesPerLeagueModel
import com.android.chandchand.presentation.utils.DAY
import com.android.chandchand.presentation.utils.getDateFromToday
import com.android.chandchand.wrapEspressoIdlingResource
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
    private val getFixturesUseCase: com.android.domain.usecase.GetFixturesUseCase,
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


    fun getFixtures() {
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
                            is com.android.data.common.Result.Success -> {
                                val fixtures = entityUiMapper.map(yesterdayResponse.data)
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        yesterdayFixtures = fixtures
                                    )
                                }
                            }
                            is com.android.data.common.Result.Error -> {
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        errorMessage = "yesterday fixtures failed!"
                                    )
                                }
                            }
                        }

                        when (val todayResponse = today.await()) {
                            is com.android.data.common.Result.Success -> {
                                val fixtures = entityUiMapper.map(todayResponse.data)
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        todayFixtures = fixtures
                                    )
                                }
                            }
                            is com.android.data.common.Result.Error -> {
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        errorMessage = "today fixtures failed!"
                                    )
                                }
                            }
                        }

                        when (val tomorrowResponse = tomorrow.await()) {
                            is com.android.data.common.Result.Success -> {
                                val fixtures = entityUiMapper.map(tomorrowResponse.data)
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        tomorrowFixtures = fixtures
                                    )
                                }
                            }
                            is com.android.data.common.Result.Error -> {
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        errorMessage = "tomorrow fixtures failed!"
                                    )
                                }
                            }
                        }

                        when (val dayAfterTomorrowResponse = dayAfterTomorrow.await()) {
                            is com.android.data.common.Result.Success -> {
                                val fixtures = entityUiMapper.map(dayAfterTomorrowResponse.data)
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        dayAfterTomorrowFixtures = fixtures
                                    )
                                }
                            }
                            is com.android.data.common.Result.Error -> {
                                updateState {
                                    it.copy(
                                        isLoading = false,
                                        errorMessage = "dayAfterTomorrow fixtures failed!"
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
                is com.android.data.common.Result.Success -> {
                    val fixtures = entityUiMapper.map(response.data)
                    updateState {
                        it.copy(
                            isLoading = false,
                            somedayFixtures = fixtures
                        )
                    }
                }
                is com.android.data.common.Result.Error -> {
                    updateState {
                        it.copy(
                            isLoading = false,
                            errorMessage = "someday fixtures failed!"
                        )
                    }
                }
            }
        }
    }

    fun onLeagueHeaderClick(model: FixturesPerLeagueModel, day: DAY) {
        val oldFixtureList = when (day) {
            DAY.YESTERDAY -> {
                _state.value.yesterdayFixtures
            }
            DAY.TODAY -> {
                _state.value.todayFixtures
            }
            DAY.TOMORROW -> {
                _state.value.tomorrowFixtures
            }
            DAY.DAY_AFTER_TOMORROW -> {
                _state.value.dayAfterTomorrowFixtures
            }
            DAY.SOMEDAY -> {
                _state.value.somedayFixtures
            }
        }

        if (oldFixtureList.isNotEmpty()) {
            val newFixtureList = oldFixtureList.map {
                if (it == model) {
                    it.copy(isExpanded = model.isExpanded.not())
                } else {
                    it
                }
            }
            viewModelScope.launch {
                when (day) {
                    DAY.YESTERDAY -> {
                        updateState { it.copy(yesterdayFixtures = newFixtureList) }
                    }
                    DAY.TODAY -> {
                        updateState { it.copy(todayFixtures = newFixtureList) }
                    }
                    DAY.TOMORROW -> {
                        updateState { it.copy(tomorrowFixtures = newFixtureList) }
                    }
                    DAY.DAY_AFTER_TOMORROW -> {
                        updateState { it.copy(dayAfterTomorrowFixtures = newFixtureList) }
                    }
                    DAY.SOMEDAY -> {
                        updateState { it.copy(somedayFixtures = newFixtureList) }
                    }
                }
            }
        }
    }

    fun setSomedayDate(date: String, dateDescription: String) {
        viewModelScope.launch {
            updateState { it.copy(somedayDate = date, somedayDateDescription = dateDescription) }
        }
    }
}