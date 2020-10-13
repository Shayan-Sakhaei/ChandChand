package com.android.chandchand.presentation.leagues

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.chandchand.data.common.Result
import com.android.chandchand.domain.entities.StandingEntity
import com.android.chandchand.domain.usecase.GetStandingsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LeaguesViewModel @ViewModelInject constructor(
    private val getStandingsUseCase: GetStandingsUseCase
) : ViewModel() {

    private val _standings = MutableLiveData<List<StandingEntity>>()
    val standings: LiveData<List<StandingEntity>> get() = _standings

    fun getStandings(leagueId: Int) {
        _standings.value = emptyList()
        viewModelScope.launch {
            getStandingsUseCase.execute(leagueId)
                .onStart { }
                .catch { }
                .collect { standingEntities ->
                    when (standingEntities) {
                        is Result.Success -> {
                            _standings.value = standingEntities.data
                        }
                        is Result.Error -> {
                        }
                    }
                }
        }
    }
}