package com.anonymous.fixtures

import app.cash.turbine.test
import com.anonymous.data.model.FixtureEntity
import com.anonymous.data.repository.fake.test.FakeFixturesRepository
import com.anonymous.domain.model.GetFixturesUseCase
import com.anonymous.fixtures.mapper.FixtureEntityUiMapper
import com.anonymous.fixtures.model.FixturesPerLeagueModel
import com.anonymous.testing.MainDispatcherRule
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FixturesViewModelTest {

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    private lateinit var getFixturesUseCase: GetFixturesUseCase
    private lateinit var mapper: FixtureEntityUiMapper
    private lateinit var viewModel: FixturesViewModel

    @Before
    fun setUp() {
        mapper = FixtureEntityUiMapper()
    }

    @Test
    fun `getFixtures Happy Path`() = runTest {
        getFixturesUseCase = GetFixturesUseCase(
            FakeFixturesRepository(
                fixtures = fakeFixtureEntities
            )
        )
        viewModel = FixturesViewModel(getFixturesUseCase, mapper)

        viewModel.state.test {

            val fixturesPerLeague: List<FixturesPerLeagueModel> = mapper.map(fakeFixtureEntities)
            val dailyFixturesState = DailyFixturesState(fixtures = fixturesPerLeague)

            val intent = FixturesIntent.GetFixtures
            viewModel.intents.send(intent)

            assertEquals(FixturesState(), awaitItem())

            assertEquals(FixturesState(isLoading = true), awaitItem())

            assertEquals(
                FixturesState(
                    isLoading = false,
                    yesterdayFixturesState = dailyFixturesState,
                ), awaitItem()
            )

            assertEquals(
                FixturesState(
                    isLoading = false,
                    yesterdayFixturesState = dailyFixturesState,
                    todayFixturesState = dailyFixturesState,
                ), awaitItem()
            )

            assertEquals(
                FixturesState(
                    isLoading = false,
                    yesterdayFixturesState = dailyFixturesState,
                    todayFixturesState = dailyFixturesState,
                    tomorrowFixturesState = dailyFixturesState
                ), awaitItem()
            )

            assertEquals(
                FixturesState(
                    isLoading = false,
                    yesterdayFixturesState = dailyFixturesState,
                    todayFixturesState = dailyFixturesState,
                    tomorrowFixturesState = dailyFixturesState,
                    dayAfterTomorrowFixturesState = dailyFixturesState,
                ), awaitItem()
            )
        }
    }


    @Test
    fun `getFixtures Unhappy Path`() = runTest {
        getFixturesUseCase = GetFixturesUseCase(FakeFixturesRepository())
        viewModel = FixturesViewModel(getFixturesUseCase, mapper)

        viewModel.state.test {

            val intent = FixturesIntent.GetFixtures
            viewModel.intents.send(intent)

            assertEquals(FixturesState(), awaitItem())

            assertEquals(FixturesState(isLoading = true), awaitItem())

            assertEquals(
                FixturesState(
                    isLoading = false,
                    yesterdayFixturesState = DailyFixturesState(errorMessage = "yesterday fixtures failed!"),
                ), awaitItem()
            )

            assertEquals(
                FixturesState(
                    isLoading = false,
                    yesterdayFixturesState = DailyFixturesState(errorMessage = "yesterday fixtures failed!"),
                    todayFixturesState = DailyFixturesState(errorMessage = "today fixtures failed!"),
                ), awaitItem()
            )

            assertEquals(
                FixturesState(
                    isLoading = false,
                    yesterdayFixturesState = DailyFixturesState(errorMessage = "yesterday fixtures failed!"),
                    todayFixturesState = DailyFixturesState(errorMessage = "today fixtures failed!"),
                    tomorrowFixturesState = DailyFixturesState(errorMessage = "tomorrow fixtures failed!")
                ), awaitItem()
            )

            assertEquals(
                FixturesState(
                    isLoading = false,
                    yesterdayFixturesState = DailyFixturesState(errorMessage = "yesterday fixtures failed!"),
                    todayFixturesState = DailyFixturesState(errorMessage = "today fixtures failed!"),
                    tomorrowFixturesState = DailyFixturesState(errorMessage = "tomorrow fixtures failed!"),
                    dayAfterTomorrowFixturesState = DailyFixturesState(
                        errorMessage = "dayAfterTomorrow fixtures failed!"
                    )
                ), awaitItem()
            )
        }
    }


    @Test
    fun `getFixtures should invoke GetFixturesUseCase`() = runTest {

        getFixturesUseCase = mockk()
        viewModel = FixturesViewModel(getFixturesUseCase, mapper)

        val intent = FixturesIntent.GetFixtures
        viewModel.intents.send(intent)

        advanceUntilIdle()

        coVerify { getFixturesUseCase.execute(any()) }
    }
}

private val fakeFixtureEntities = listOf(
    FixtureEntity(
        844125,
        4640,
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