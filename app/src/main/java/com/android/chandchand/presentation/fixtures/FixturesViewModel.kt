package com.android.chandchand.presentation.fixtures

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.usecase.GetFixturesUseCase
import com.android.chandchand.presentation.common.IModel
import com.android.chandchand.presentation.mapper.FixtureEntityUiMapper
import com.android.chandchand.presentation.model.DateModel
import com.android.chandchand.presentation.model.LeagueModel
import com.android.chandchand.presentation.utils.WeekDay
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
                is FixturesIntent.GetFixtures -> getFixtures(it.date, it.weekDay)
            }
        }
    }

    val _somedayDateModel = MutableLiveData<DateModel>()
    val somedayDateModel: LiveData<DateModel> get() = _somedayDateModel

    private suspend fun updateState(handler: suspend (intent: FixturesState) -> FixturesState) {
        _state.value = handler(state.value)
    }


    fun getFixtures(date: String, weekDay: WeekDay) {
        viewModelScope.launch {
            try {
                updateState { it.copy(isLoading = true) }
                getFixturesUseCase.execute(date)
                    .onStart {}
                    .catch {}
                    .collect { fixtureEntities ->
                        when (fixtureEntities) {
                            is Result.Success -> {
                                when (weekDay) {
                                    is WeekDay.Yesterday -> {
                                        val yesterdayFixtures = entityUiMapper.map(
                                            fixtureEntities.data
                                        )
                                        updateState {
                                            it.copy(
                                                isLoading = false,
                                                yesterdayFixtures = yesterdayFixtures
                                            )
                                        }
                                    }
                                    is WeekDay.Today -> {
                                        val todayFixtures =
                                            entityUiMapper.map(
                                                fixtureEntities.data
                                            )
                                        updateState {
                                            it.copy(
                                                isLoading = false,
                                                todayFixtures = todayFixtures
                                            )
                                        }
                                    }
                                    is WeekDay.Tomorrow -> {
                                        val tomorrowFixtures =
                                            entityUiMapper.map(
                                                fixtureEntities.data
                                            )
                                        updateState {
                                            it.copy(
                                                isLoading = false,
                                                tomorrowFixtures = tomorrowFixtures
                                            )
                                        }
                                    }
                                    is WeekDay.DayAfterTomorrow -> {
                                        val dayAfterTomorrowFixtures =
                                            entityUiMapper.map(
                                                fixtureEntities.data
                                            )
                                        updateState {
                                            it.copy(
                                                isLoading = false,
                                                dayAfterTomorrowFixtures = dayAfterTomorrowFixtures
                                            )
                                        }
                                    }
                                    is WeekDay.Someday -> {
                                        val somedayFixtures =
                                            entityUiMapper.map(
                                                fixtureEntities.data
                                            )
                                        updateState {
                                            it.copy(
                                                isLoading = false,
                                                somedayFixtures = somedayFixtures
                                            )
                                        }
                                    }
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


    fun yesterdayLeagueTapped(leagueModel: LeagueModel) {
        val oldFixturesPerLeague = _state.value.yesterdayFixtures
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
                updateState { it.copy(yesterdayFixtures = newFixtureList) }
            }
        }
    }

    fun todayLeagueTapped(leagueModel: LeagueModel) {
        val oldFixturesPerLeague = _state.value.todayFixtures
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
                updateState { it.copy(todayFixtures = newFixtureList) }
            }
        }
    }

    fun tomorrowLeagueTapped(leagueModel: LeagueModel) {
        val oldFixturesPerLeague = _state.value.tomorrowFixtures
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
                updateState { it.copy(tomorrowFixtures = newFixtureList) }
            }
        }
    }

    fun dayAfterTomorrowLeagueTapped(leagueModel: LeagueModel) {
        val oldFixturesPerLeague = _state.value.dayAfterTomorrowFixtures
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
                updateState { it.copy(dayAfterTomorrowFixtures = newFixtureList) }
            }
        }
    }

    fun somedayLeagueTapped(leagueModel: LeagueModel) {
        val oldFixturesPerLeague = _state.value.somedayFixtures
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
                updateState { it.copy(somedayFixtures = newFixtureList) }
            }
        }
    }
}