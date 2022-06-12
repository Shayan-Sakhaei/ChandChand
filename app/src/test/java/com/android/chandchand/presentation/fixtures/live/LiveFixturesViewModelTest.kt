package com.android.chandchand.presentation.fixtures.live

import androidx.lifecycle.viewModelScope
import com.android.chandchand.MainCoroutineRule
import com.android.data.fixtures.entity.LiveFixEvents
import com.android.chandchand.data.fixtures.repository.FakeFixturesRepository
import com.android.domain.entities.LiveFixtureEntities
import com.android.domain.entities.LiveFixtureEntity
import com.android.domain.usecase.GetLiveFixturesUseCase
import com.android.chandchand.presentation.mapper.LiveFixtureEntityUiMapper
import com.android.chandchand.presentation.model.LiveFixturesPerLeagueModels
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

    private lateinit var getLiveFixturesUseCase: com.android.domain.usecase.GetLiveFixturesUseCase
    private lateinit var mapper: LiveFixtureEntityUiMapper
    private lateinit var viewModel: LiveFixturesViewModel

    @Before
    fun setUp() {
        mapper = LiveFixtureEntityUiMapper()
    }

    @Test
    fun `getLiveFixtures Happy Path`() = mainCoroutineRule.runBlockingTest {
        getLiveFixturesUseCase = com.android.domain.usecase.GetLiveFixturesUseCase(
            FakeFixturesRepository(liveFixtures = fakeLiveFixtureEntities)
        )
        viewModel = LiveFixturesViewModel(getLiveFixturesUseCase, mapper)

        val states = mutableListOf<LiveFixturesState>()

        viewModel.viewModelScope.launch {
            viewModel.state.toList(states)
        }

        val liveFixturesPerLeague: LiveFixturesPerLeagueModels = mapper.map(fakeLiveFixtureEntities)

        val assertions = listOf(
            LiveFixturesState(),
            LiveFixturesState(isLoading = true),
            LiveFixturesState(isLoading = false, liveFixtures = liveFixturesPerLeague)
        )

        val intent = LiveFixturesIntent.GetLiveFixtures
        viewModel.intents.send(intent)

        Assert.assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            Assert.assertEquals(assertion, state)
        }
    }


    @Test
    fun `getLiveFixtures Unhappy Path`() = mainCoroutineRule.runBlockingTest {
        getLiveFixturesUseCase =
            com.android.domain.usecase.GetLiveFixturesUseCase(FakeFixturesRepository())
        viewModel = LiveFixturesViewModel(getLiveFixturesUseCase, mapper)

        val states = mutableListOf<LiveFixturesState>()

        viewModel.viewModelScope.launch {
            viewModel.state.toList(states)
        }

        val assertions = listOf(
            LiveFixturesState(),
            LiveFixturesState(isLoading = true),
            LiveFixturesState(isLoading = false, errorMessage = "failed!")
        )

        val intent = LiveFixturesIntent.GetLiveFixtures
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
            viewModel = LiveFixturesViewModel(getLiveFixturesUseCase, mapper)

            val intent = LiveFixturesIntent.GetLiveFixtures
            viewModel.intents.send(intent)

            coVerify { getLiveFixturesUseCase.execute() }
        }
}

private val fakeLiveFixtureEntities = com.android.domain.entities.LiveFixtureEntities(
    1, listOf(
        com.android.domain.entities.LiveFixtureEntity(
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
                com.android.data.fixtures.entity.LiveFixEvents(
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