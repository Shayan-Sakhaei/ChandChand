package com.android.chandchand.presentation.fixtures

import androidx.lifecycle.viewModelScope
import com.android.chandchand.MainCoroutineRule
import com.android.chandchand.presentation.fake.FakeFixturesRepository
import com.android.chandchand.presentation.mapper.FixtureEntityUiMapper
import com.android.chandchand.presentation.model.DailyFixturesState
import com.android.chandchand.presentation.model.FixturesPerLeagueModel
import com.android.domain.entities.FixtureEntity
import com.android.domain.usecase.GetFixturesUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FixturesViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var getFixturesUseCase: GetFixturesUseCase
    private lateinit var mapper: FixtureEntityUiMapper
    private lateinit var viewModel: FixturesViewModel

    @Before
    fun setUp() {
        mapper = FixtureEntityUiMapper()
    }

    @Test
    fun `getFixtures Happy Path`() = mainCoroutineRule.runBlockingTest {
        getFixturesUseCase = GetFixturesUseCase(
            FakeFixturesRepository(
                fixtures = fakeFixtureEntities
            )
        )
        viewModel = FixturesViewModel(getFixturesUseCase, mapper)

        val states = mutableListOf<FixturesState>()

        viewModel.viewModelScope.launch {
            viewModel.state.toList(states)
        }

        val fixturesPerLeague: List<FixturesPerLeagueModel> = mapper.map(fakeFixtureEntities)
        val dailyFixturesState = DailyFixturesState(fixtures = fixturesPerLeague)

        val assertions = listOf(
            FixturesState(),
            FixturesState(isLoading = true),
            FixturesState(
                isLoading = false,
                yesterdayFixturesState = dailyFixturesState,
            ),
            FixturesState(
                isLoading = false,
                yesterdayFixturesState = dailyFixturesState,
                todayFixturesState = dailyFixturesState,
            ),
            FixturesState(
                isLoading = false,
                yesterdayFixturesState = dailyFixturesState,
                todayFixturesState = dailyFixturesState,
                tomorrowFixturesState = dailyFixturesState
            ),
            FixturesState(
                isLoading = false,
                yesterdayFixturesState = dailyFixturesState,
                todayFixturesState = dailyFixturesState,
                tomorrowFixturesState = dailyFixturesState,
                dayAfterTomorrowFixturesState = dailyFixturesState,
            )
        )

        val intent = FixturesIntent.GetFixtures
        viewModel.intents.send(intent)

        assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            assertEquals(assertion, state)
        }
    }


    @Test
    fun `getFixtures Unhappy Path`() = mainCoroutineRule.runBlockingTest {
        getFixturesUseCase = GetFixturesUseCase(FakeFixturesRepository())
        viewModel = FixturesViewModel(getFixturesUseCase, mapper)

        val states = mutableListOf<FixturesState>()

        viewModel.viewModelScope.launch {
            viewModel.state.toList(states)
        }

        val assertions = listOf(
            FixturesState(),
            FixturesState(isLoading = true),
            FixturesState(
                isLoading = false,
                yesterdayFixturesState = DailyFixturesState(errorMessage = "yesterday fixtures failed!"),
            ),
            FixturesState(
                isLoading = false,
                yesterdayFixturesState = DailyFixturesState(errorMessage = "yesterday fixtures failed!"),
                todayFixturesState = DailyFixturesState(errorMessage = "today fixtures failed!"),
            ),
            FixturesState(
                isLoading = false,
                yesterdayFixturesState = DailyFixturesState(errorMessage = "yesterday fixtures failed!"),
                todayFixturesState = DailyFixturesState(errorMessage = "today fixtures failed!"),
                tomorrowFixturesState = DailyFixturesState(errorMessage = "tomorrow fixtures failed!")
            ),
            FixturesState(
                isLoading = false,
                yesterdayFixturesState = DailyFixturesState(errorMessage = "yesterday fixtures failed!"),
                todayFixturesState = DailyFixturesState(errorMessage = "today fixtures failed!"),
                tomorrowFixturesState = DailyFixturesState(errorMessage = "tomorrow fixtures failed!"),
                dayAfterTomorrowFixturesState = DailyFixturesState(errorMessage = "dayAfterTomorrow fixtures failed!"),
            )
        )

        val intent = FixturesIntent.GetFixtures
        viewModel.intents.send(intent)

        assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            assertEquals(assertion, state)
        }
    }

    @Test
    fun `getFixtures should invoke GetFixturesUseCase`() =
        mainCoroutineRule.runBlockingTest {

            getFixturesUseCase = mockk()
            viewModel = FixturesViewModel(getFixturesUseCase, mapper)

            val intent = FixturesIntent.GetFixtures
            viewModel.intents.send(intent)

            coVerify { getFixturesUseCase.execute(any()) }
        }
}

private val fakeFixtureEntities = listOf(
    FixtureEntity(
        844125,
        3935,
        "Persian Gulf Cup",
        "Iran",
        "https://media.api-sports.io/football/leagues/290.png",
        "2022-03-17T17:30:00+03:30",
        1647525600,
        "Regular Season - 23",
        "Match Finished",
        "FT",
        90,
        "Azadi Stadium",
        "M. Seyedali",
        2742,
        "Persepolis FC",
        "https://media.api-sports.io/football/teams/2742.png",
        2733,
        "Esteghlal FC",
        "https://media.api-sports.io/football/teams/2733.png",
        "1",
        "1",
        "1-0",
        "1-1",
        null,
        null
    )
)