package com.anonymous.fixtures

import app.cash.turbine.test
import com.anonymous.data.model.LiveFixtureEntities
import com.anonymous.data.model.LiveFixtureEntity
import com.anonymous.data.model.LiveFixtureEventsEntity
import com.anonymous.data.repository.fake.test.FakeFixturesRepository
import com.anonymous.domain.model.GetLiveFixturesUseCase
import com.anonymous.fixtures.mapper.LiveFixtureEntityUiMapper
import com.anonymous.fixtures.model.LiveFixturesPerLeagueModels
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
class LiveFixturesViewModelTest {

    @get:Rule
    var mainDispatcherRule = MainDispatcherRule()

    private lateinit var getLiveFixturesUseCase: GetLiveFixturesUseCase
    private lateinit var mapper: LiveFixtureEntityUiMapper
    private lateinit var viewModel: LiveFixturesViewModel

    @Before
    fun setUp() {
        mapper = LiveFixtureEntityUiMapper()
    }


    @Test
    fun `getLiveFixtures Happy Path`() = runTest {
        getLiveFixturesUseCase = GetLiveFixturesUseCase(
            FakeFixturesRepository(liveFixtures = fakeLiveFixtureEntities)
        )
        viewModel = LiveFixturesViewModel(getLiveFixturesUseCase, mapper)

        viewModel.state.test {

            val liveFixturesPerLeague: LiveFixturesPerLeagueModels =
                mapper.map(fakeLiveFixtureEntities)

            val intent = LiveFixturesIntent.GetLiveFixtures
            viewModel.intents.send(intent)

            assertEquals(LiveFixturesState(), awaitItem())

            assertEquals(LiveFixturesState(isLoading = true), awaitItem())

            assertEquals(
                LiveFixturesState(
                    isLoading = false, liveFixtures = liveFixturesPerLeague
                ), awaitItem()
            )
        }
    }


    @Test
    fun `getLiveFixtures Unhappy Path`() = runTest {
        getLiveFixturesUseCase = GetLiveFixturesUseCase(FakeFixturesRepository())
        viewModel = LiveFixturesViewModel(getLiveFixturesUseCase, mapper)

        val intent = LiveFixturesIntent.GetLiveFixtures
        viewModel.intents.send(intent)

        viewModel.state.test {

            assertEquals(LiveFixturesState(), awaitItem())

            assertEquals(LiveFixturesState(isLoading = true), awaitItem())

            assertEquals(
                LiveFixturesState(isLoading = false, errorMessage = "failed!"),
                awaitItem()
            )
        }
    }


    @Test
    fun `getLiveFixtures should invoke GetLiveFixturesUseCase`() = runTest {

        getLiveFixturesUseCase = mockk()
        viewModel = LiveFixturesViewModel(getLiveFixturesUseCase, mapper)

        val intent = LiveFixturesIntent.GetLiveFixtures
        viewModel.intents.send(intent)

        advanceUntilIdle()

        coVerify { getLiveFixturesUseCase.execute() }
    }
}

private val fakeLiveFixtureEntities = LiveFixtureEntities(
    1, listOf(
        LiveFixtureEntity(
            836275,
            4067,
            "J. League Div.1",
            "Japan",
            "https://media.api-sports.io/football/leagues/98.png",
            "2022-04-17T07:00:00+00:00",
            1650178800,
            "Regular Season - 9",
            "Second Half",
            "2H",
            66,
            "Yamaha Stadium",
            "Ryo Tanimoto, Japan",
            280,
            "Jubilo Iwata",
            "https://media.api-sports.io/football/teams/280.png",
            282,
            "Sanfrecce Hiroshima",
            "https://media.api-sports.io/football/teams/282.png",
            "1",
            "2",
            "1-0",
            null,
            null,
            null,
            listOf(
                LiveFixtureEventsEntity(
                    39,
                    null,
                    280,
                    "Jubilo Iwata",
                    "32965",
                    "Y. Suzuki",
                    "10559",
                    "Ricardo Graca",
                    "Goal",
                    "Normal Goal",
                    null
                )
            )
        )
    )
)