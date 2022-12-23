package com.android.chandchand.presentation.fixtures.live

import androidx.lifecycle.viewModelScope
import com.android.chandchand.MainCoroutineRule
import com.android.chandchand.presentation.fake.FakeFixturesRepository
import com.anonymous.fixtures.LiveFixturesIntent
import com.anonymous.fixtures.LiveFixturesState
import com.anonymous.fixtures.LiveFixturesViewModel
import com.android.chandchand.presentation.mapper.LiveFixtureEntityUiMapper
import com.anonymous.ui.model.LiveFixturesPerLeagueModels
import com.anonymous.data.model.LiveFixtureEventsEntity
import com.anonymous.domain.model.GetLiveFixturesUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LiveFixturesViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var getLiveFixturesUseCase: com.anonymous.domain.model.GetLiveFixturesUseCase
    private lateinit var mapper: LiveFixtureEntityUiMapper
    private lateinit var viewModel: com.anonymous.fixtures.LiveFixturesViewModel

    @Before
    fun setUp() {
        mapper = LiveFixtureEntityUiMapper()
    }

    @Test
    fun `getLiveFixtures Happy Path`() = mainCoroutineRule.runBlockingTest {
        getLiveFixturesUseCase = com.anonymous.domain.model.GetLiveFixturesUseCase(
            FakeFixturesRepository(liveFixtures = fakeLiveFixtureEntities)
        )
        viewModel = com.anonymous.fixtures.LiveFixturesViewModel(getLiveFixturesUseCase, mapper)

        val states = mutableListOf<com.anonymous.fixtures.LiveFixturesState>()

        viewModel.viewModelScope.launch {
            viewModel.state.toList(states)
        }

        val liveFixturesPerLeague: com.anonymous.ui.model.LiveFixturesPerLeagueModels = mapper.map(fakeLiveFixtureEntities)

        val assertions = listOf(
            com.anonymous.fixtures.LiveFixturesState(),
            com.anonymous.fixtures.LiveFixturesState(isLoading = true),
            com.anonymous.fixtures.LiveFixturesState(
                isLoading = false,
                liveFixtures = liveFixturesPerLeague
            )
        )

        val intent = com.anonymous.fixtures.LiveFixturesIntent.GetLiveFixtures
        viewModel.intents.send(intent)

        Assert.assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            Assert.assertEquals(assertion, state)
        }
    }


    @Test
    fun `getLiveFixtures Unhappy Path`() = mainCoroutineRule.runBlockingTest {
        getLiveFixturesUseCase =
            com.anonymous.domain.model.GetLiveFixturesUseCase(FakeFixturesRepository())
        viewModel = com.anonymous.fixtures.LiveFixturesViewModel(getLiveFixturesUseCase, mapper)

        val states = mutableListOf<com.anonymous.fixtures.LiveFixturesState>()

        viewModel.viewModelScope.launch {
            viewModel.state.toList(states)
        }

        val assertions = listOf(
            com.anonymous.fixtures.LiveFixturesState(),
            com.anonymous.fixtures.LiveFixturesState(isLoading = true),
            com.anonymous.fixtures.LiveFixturesState(isLoading = false, errorMessage = "failed!")
        )

        val intent = com.anonymous.fixtures.LiveFixturesIntent.GetLiveFixtures
        viewModel.intents.send(intent)

        Assert.assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            Assert.assertEquals(assertion, state)
        }
    }


    @Test
    fun `getLiveFixtures should invoke GetLiveFixturesUseCase`() =
        mainCoroutineRule.runBlockingTest {

            getLiveFixturesUseCase = mockk()
            viewModel = com.anonymous.fixtures.LiveFixturesViewModel(getLiveFixturesUseCase, mapper)

            val intent = com.anonymous.fixtures.LiveFixturesIntent.GetLiveFixtures
            viewModel.intents.send(intent)

            coVerify { getLiveFixturesUseCase.execute() }
        }
}

private val fakeLiveFixtureEntities = com.anonymous.data.model.LiveFixtureEntities(
    1, listOf(
        com.anonymous.data.model.LiveFixtureEntity(
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
                com.anonymous.data.model.LiveFixtureEventsEntity(
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