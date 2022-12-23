package com.android.chandchand.presentation.fixtures

import androidx.lifecycle.viewModelScope
import com.android.chandchand.MainCoroutineRule
import com.android.chandchand.presentation.fake.FakeFixturesRepository
import com.android.chandchand.presentation.mapper.FixtureEntityUiMapper
import com.anonymous.ui.model.DailyFixturesState
import com.anonymous.ui.model.FixturesPerLeagueModel
import com.anonymous.data.model.FixtureEntity
import com.anonymous.domain.model.GetFixturesUseCase
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

    private lateinit var getFixturesUseCase: com.anonymous.domain.model.GetFixturesUseCase
    private lateinit var mapper: FixtureEntityUiMapper
    private lateinit var viewModel: com.anonymous.fixtures.FixturesViewModel

    @Before
    fun setUp() {
        mapper = FixtureEntityUiMapper()
    }

    @Test
    fun `getFixtures Happy Path`() = mainCoroutineRule.runBlockingTest {
        getFixturesUseCase = com.anonymous.domain.model.GetFixturesUseCase(
            FakeFixturesRepository(
                fixtures = fakeFixtureEntities
            )
        )
        viewModel = com.anonymous.fixtures.FixturesViewModel(getFixturesUseCase, mapper)

        val states = mutableListOf<com.anonymous.fixtures.FixturesState>()

        viewModel.viewModelScope.launch {
            viewModel.state.toList(states)
        }

        val fixturesPerLeague: List<com.anonymous.ui.model.FixturesPerLeagueModel> = mapper.map(fakeFixtureEntities)
        val dailyFixturesState =
            com.anonymous.ui.model.DailyFixturesState(fixtures = fixturesPerLeague)

        val assertions = listOf(
            com.anonymous.fixtures.FixturesState(),
            com.anonymous.fixtures.FixturesState(isLoading = true),
            com.anonymous.fixtures.FixturesState(
                isLoading = false,
                yesterdayFixturesState = dailyFixturesState,
            ),
            com.anonymous.fixtures.FixturesState(
                isLoading = false,
                yesterdayFixturesState = dailyFixturesState,
                todayFixturesState = dailyFixturesState,
            ),
            com.anonymous.fixtures.FixturesState(
                isLoading = false,
                yesterdayFixturesState = dailyFixturesState,
                todayFixturesState = dailyFixturesState,
                tomorrowFixturesState = dailyFixturesState
            ),
            com.anonymous.fixtures.FixturesState(
                isLoading = false,
                yesterdayFixturesState = dailyFixturesState,
                todayFixturesState = dailyFixturesState,
                tomorrowFixturesState = dailyFixturesState,
                dayAfterTomorrowFixturesState = dailyFixturesState,
            )
        )

        val intent = com.anonymous.fixtures.FixturesIntent.GetFixtures
        viewModel.intents.send(intent)

        assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            assertEquals(assertion, state)
        }
    }


    @Test
    fun `getFixtures Unhappy Path`() = mainCoroutineRule.runBlockingTest {
        getFixturesUseCase = com.anonymous.domain.model.GetFixturesUseCase(FakeFixturesRepository())
        viewModel = com.anonymous.fixtures.FixturesViewModel(getFixturesUseCase, mapper)

        val states = mutableListOf<com.anonymous.fixtures.FixturesState>()

        viewModel.viewModelScope.launch {
            viewModel.state.toList(states)
        }

        val assertions = listOf(
            com.anonymous.fixtures.FixturesState(),
            com.anonymous.fixtures.FixturesState(isLoading = true),
            com.anonymous.fixtures.FixturesState(
                isLoading = false,
                yesterdayFixturesState = com.anonymous.ui.model.DailyFixturesState(errorMessage = "yesterday fixtures failed!"),
            ),
            com.anonymous.fixtures.FixturesState(
                isLoading = false,
                yesterdayFixturesState = com.anonymous.ui.model.DailyFixturesState(errorMessage = "yesterday fixtures failed!"),
                todayFixturesState = com.anonymous.ui.model.DailyFixturesState(errorMessage = "today fixtures failed!"),
            ),
            com.anonymous.fixtures.FixturesState(
                isLoading = false,
                yesterdayFixturesState = com.anonymous.ui.model.DailyFixturesState(errorMessage = "yesterday fixtures failed!"),
                todayFixturesState = com.anonymous.ui.model.DailyFixturesState(errorMessage = "today fixtures failed!"),
                tomorrowFixturesState = com.anonymous.ui.model.DailyFixturesState(errorMessage = "tomorrow fixtures failed!")
            ),
            com.anonymous.fixtures.FixturesState(
                isLoading = false,
                yesterdayFixturesState = com.anonymous.ui.model.DailyFixturesState(errorMessage = "yesterday fixtures failed!"),
                todayFixturesState = com.anonymous.ui.model.DailyFixturesState(errorMessage = "today fixtures failed!"),
                tomorrowFixturesState = com.anonymous.ui.model.DailyFixturesState(errorMessage = "tomorrow fixtures failed!"),
                dayAfterTomorrowFixturesState = com.anonymous.ui.model.DailyFixturesState(
                    errorMessage = "dayAfterTomorrow fixtures failed!"
                ),
            )
        )

        val intent = com.anonymous.fixtures.FixturesIntent.GetFixtures
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
            viewModel = com.anonymous.fixtures.FixturesViewModel(getFixturesUseCase, mapper)

            val intent = com.anonymous.fixtures.FixturesIntent.GetFixtures
            viewModel.intents.send(intent)

            coVerify { getFixturesUseCase.execute(any()) }
        }
}

private val fakeFixtureEntities = listOf(
    com.anonymous.data.model.FixtureEntity(
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