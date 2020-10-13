package com.android.chandchand.presentation.fixtures

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.usecase.GetFixturesUseCase
import com.android.chandchand.presentation.mapper.FixtureEntityUiMapper
import com.android.chandchand.presentation.model.DateModel
import com.android.chandchand.presentation.model.FixturesPerLeagueModel
import com.android.chandchand.presentation.model.LeagueModel
import com.android.chandchand.presentation.utils.WeekDay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FixturesViewModel @ViewModelInject constructor(
    private val getFixturesUseCase: GetFixturesUseCase,
    private val entityUiMapper: FixtureEntityUiMapper
) : ViewModel() {

    private val _yesterdayFixtures = MutableLiveData<List<FixturesPerLeagueModel>>()
    val yesterdayFixtures: LiveData<List<FixturesPerLeagueModel>> get() = _yesterdayFixtures

    private val _todayFixtures = MutableLiveData<List<FixturesPerLeagueModel>>()
    val todayFixtures: LiveData<List<FixturesPerLeagueModel>> get() = _todayFixtures

    private val _tomorrowFixtures = MutableLiveData<List<FixturesPerLeagueModel>>()
    val tomorrowFixtures: LiveData<List<FixturesPerLeagueModel>> get() = _tomorrowFixtures

    private val _dayAfterTomorrowFixtures = MutableLiveData<List<FixturesPerLeagueModel>>()
    val dayAfterTomorrowFixtures: LiveData<List<FixturesPerLeagueModel>> get() = _dayAfterTomorrowFixtures

    val _somedayDateModel = MutableLiveData<DateModel>()
    val somedayDateModel: LiveData<DateModel> get() = _somedayDateModel

    private val _somedayFixtures = MutableLiveData<List<FixturesPerLeagueModel>>()
    val somedayFixtures: LiveData<List<FixturesPerLeagueModel>> get() = _somedayFixtures


    fun getFixtures(date: String, weekDay: WeekDay) {
        viewModelScope.launch {
            getFixturesUseCase.execute(date)
                .onStart { }
                .catch { }
                .collect { fixtureEntities ->
                    when (fixtureEntities) {
                        is Result.Success -> {
                            when (weekDay) {
                                is WeekDay.Yesterday -> {
                                    _yesterdayFixtures.value =
                                        entityUiMapper.map(
                                            fixtureEntities.data
                                        )
                                }
                                is WeekDay.Today -> {
                                    _todayFixtures.value =
                                        entityUiMapper.map(
                                            fixtureEntities.data
                                        )
                                }
                                is WeekDay.Tomorrow -> {
                                    _tomorrowFixtures.value =
                                        entityUiMapper.map(
                                            fixtureEntities.data
                                        )
                                }
                                is WeekDay.DayAfterTomorrow -> {
                                    _dayAfterTomorrowFixtures.value =
                                        entityUiMapper.map(
                                            fixtureEntities.data
                                        )
                                }
                                is WeekDay.Someday -> {
                                    _somedayFixtures.value =
                                        entityUiMapper.map(
                                            fixtureEntities.data
                                        )
                                }
                            }
                        }
                        is Result.Error -> {
                        }
                    }
                }
        }
    }


    fun yesterdayLeagueTapped(leagueModel: LeagueModel) {
        val oldFixturesPerLeague = _yesterdayFixtures.value
        if (oldFixturesPerLeague != null) {
            val newLeague = leagueModel.copy(isExpanded = leagueModel.isExpanded.not())
            val newFixtureList = oldFixturesPerLeague.map {
                if (it.leagueModel == leagueModel) {
                    it.copy(leagueModel = newLeague)
                } else {
                    it
                }
            }
            _yesterdayFixtures.value = newFixtureList
        }
    }

    fun todayLeagueTapped(leagueModel: LeagueModel) {
        val oldFixturesPerLeague = _todayFixtures.value
        if (oldFixturesPerLeague != null) {
            val newLeague = leagueModel.copy(isExpanded = leagueModel.isExpanded.not())
            val newFixtureList = oldFixturesPerLeague.map {
                if (it.leagueModel == leagueModel) {
                    it.copy(leagueModel = newLeague)
                } else {
                    it
                }
            }
            _todayFixtures.value = newFixtureList
        }
    }

    fun tomorrowLeagueTapped(leagueModel: LeagueModel) {
        val oldFixturesPerLeague = _tomorrowFixtures.value
        if (oldFixturesPerLeague != null) {
            val newLeague = leagueModel.copy(isExpanded = leagueModel.isExpanded.not())
            val newFixtureList = oldFixturesPerLeague.map {
                if (it.leagueModel == leagueModel) {
                    it.copy(leagueModel = newLeague)
                } else {
                    it
                }
            }
            _tomorrowFixtures.value = newFixtureList
        }
    }

    fun dayAfterTomorrowLeagueTapped(leagueModel: LeagueModel) {
        val oldFixturesPerLeague = _dayAfterTomorrowFixtures.value
        if (oldFixturesPerLeague != null) {
            val newLeague = leagueModel.copy(isExpanded = leagueModel.isExpanded.not())
            val newFixtureList = oldFixturesPerLeague.map {
                if (it.leagueModel == leagueModel) {
                    it.copy(leagueModel = newLeague)
                } else {
                    it
                }
            }
            _dayAfterTomorrowFixtures.value = newFixtureList
        }
    }

    fun somedayLeagueTapped(leagueModel: LeagueModel) {
        val oldFixturesPerLeague = _somedayFixtures.value
        if (oldFixturesPerLeague != null) {
            val newLeague = leagueModel.copy(isExpanded = leagueModel.isExpanded.not())
            val newFixtureList = oldFixturesPerLeague.map {
                if (it.leagueModel == leagueModel) {
                    it.copy(leagueModel = newLeague)
                } else {
                    it
                }
            }
            _somedayFixtures.value = newFixtureList
        }
    }
}