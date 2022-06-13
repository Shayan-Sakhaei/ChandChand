package com.android.data.fixtures.repository

import com.android.data.datasources.FixturesDataSource
import com.android.data.fixtures.datasource.FakeFixturesDataSource
import com.android.data.fixtures.datasource.RemoteFixturesDataSource
import com.android.data.fixtures.entity.*
import com.android.data.fixtures.mapper.FixtureServerEntityMapper
import com.android.data.fixtures.mapper.LiveFixEventsServerEntityMapper
import com.android.data.fixtures.mapper.LiveFixtureServerEntityMapper
import com.android.domain.common.Result
import com.android.domain.entities.FixtureEntity
import com.android.domain.entities.LiveFixtureEntities
import com.android.domain.entities.LiveFixtureEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class FixturesRepositoryImplTest {

    private lateinit var dataSource: FixturesDataSource
    private lateinit var fixturesMapper: FixtureServerEntityMapper
    private lateinit var liveFixEventsMapper: LiveFixEventsServerEntityMapper
    private lateinit var liveFixturesMapper: LiveFixtureServerEntityMapper
    private lateinit var repository: FixturesRepositoryImpl

    @Before
    fun setUp() {
        fixturesMapper = FixtureServerEntityMapper()
        liveFixEventsMapper = LiveFixEventsServerEntityMapper()
        liveFixturesMapper = LiveFixtureServerEntityMapper(liveFixEventsMapper)
    }

    @Test
    fun `getFixtures Happy Path`() = runBlockingTest {
        dataSource = FakeFixturesDataSource(fixturesServerModel = fakeServerFixtures)
        repository = FixturesRepositoryImpl(
            dataSource,
            fixturesMapper,
            liveFixturesMapper
        )

        val states = repository.getFixtures("2022-03-17").toList()

        val fixtureEntityList: List<FixtureEntity> =
            fakeServerFixtures.api.fixtures.map { fixFixture ->
                fixturesMapper.map(fixFixture)
            }

        val assertions = listOf(Result.Success(fixtureEntityList))

        assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            assertEquals(assertion, state)
        }
    }


    @Test
    fun `getFixtures Unhappy Path`() = runBlockingTest {
        dataSource = FakeFixturesDataSource()
        repository = FixturesRepositoryImpl(
            dataSource,
            fixturesMapper,
            liveFixturesMapper
        )

        val states = repository.getFixtures("2022-03-17").toList()

        val assertions = listOf(Result.Error(""))

        assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            assertEquals(assertion, state)
        }
    }


    @Test
    fun `getLiveFixtures Happy Path`() = runBlockingTest {
        dataSource = FakeFixturesDataSource(liveFixturesServerModel = fakeLiveServerFixtures)
        repository = FixturesRepositoryImpl(
            dataSource,
            fixturesMapper,
            liveFixturesMapper
        )

        val states = repository.getLiveFixtures().toList()

        val liveFixtureEntityList: List<LiveFixtureEntity> =
            fakeLiveServerFixtures.api.fixtures.map { liveFixFixtures ->
                liveFixturesMapper.map(liveFixFixtures)
            }

        val assertions = listOf(
            Result.Success(
                LiveFixtureEntities(
                    fakeLiveServerFixtures.api.results,
                    liveFixtureEntityList
                )
            )
        )

        assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            assertEquals(assertion, state)
        }
    }


    @Test
    fun `getLiveFixtures Unhappy Path`() = runBlockingTest {
        dataSource = FakeFixturesDataSource()
        repository = FixturesRepositoryImpl(
            dataSource,
            fixturesMapper,
            liveFixturesMapper
        )

        val states = repository.getLiveFixtures().toList()

        val assertions = listOf(Result.Error(""))

        assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            assertEquals(assertion, state)
        }
    }

    @Test
    fun `getFixtures should invoke FixturesDataSource getFixturesByDate`() = runBlockingTest {

        val fixtureEntityList: List<FixtureEntity> =
            fakeServerFixtures.api.fixtures.map { fixFixture ->
                fixturesMapper.map(fixFixture)
            }

        val expected = Result.Success(fixtureEntityList)

        dataSource = mockk<RemoteFixturesDataSource>()
        coEvery { dataSource.getFixturesByDate("2022-03-17") } returns Response.success(
            fakeServerFixtures
        )

        repository = FixturesRepositoryImpl(
            dataSource,
            fixturesMapper,
            liveFixturesMapper
        )

        val actual = repository.getFixtures("2022-03-17").first()

        coVerify { dataSource.getFixturesByDate("2022-03-17") }
        assertEquals(expected, actual)
    }

    @Test
    fun `getLiveFixtures should invoke FixturesDataSource getLiveFixtures`() = runBlockingTest {

        val liveFixtureEntityList: List<LiveFixtureEntity> =
            fakeLiveServerFixtures.api.fixtures.map { liveFixFixtures ->
                liveFixturesMapper.map(liveFixFixtures)
            }

        val expected = Result.Success(
            LiveFixtureEntities(
                fakeLiveServerFixtures.api.results,
                liveFixtureEntityList
            )
        )

        dataSource = mockk<RemoteFixturesDataSource>()
        coEvery { dataSource.getLiveFixtures() } returns Response.success(fakeLiveServerFixtures)

        repository = FixturesRepositoryImpl(
            dataSource,
            fixturesMapper,
            liveFixturesMapper
        )

        val actual = repository.getLiveFixtures().first()

        coVerify { dataSource.getLiveFixtures() }
        assertEquals(expected, actual)
    }
}

private val fakeServerFixtures = FixturesServerModel(
    FixApi(
        results = 130,
        fixtures = listOf(
            FixFixtures(
                fixture_id = 844125,
                league_id = 3935,
                league = FixLeague(),
                event_date = "2022-03-17T14:00:00+00:00",
                event_timestamp = 1647525600,
                firstHalfStart = "1647525600",
                secondHalfStart = "1647529200",
                round = "Regular Season - 23",
                status = "Match Finished",
                statusShort = "FT",
                elapsed = 90,
                venue = "Azadi Stadium",
                referee = "M. Seyedali",
                homeTeam = FixHomeTeam(),
                awayTeam = FixAwayTeam(),
                goalsHomeTeam = "1",
                goalsAwayTeam = "1",
                score = FixScore()
            )
        )
    )
)


private val fakeLiveServerFixtures = LiveFixturesServerModel(
    LiveFixApi(
        results = 2,
        fixtures = listOf(
            LiveFixFixtures(
                fixture_id = 823624,
                league_id = 3645,
                league = LiveFixLeague(
                    name = "1. Liga U19",
                    country = "Czech-Republic",
                    logo = "https://media.api-sports.io/football/leagues/668.png",
                    flag = "https://media.api-sports.io/flags/cz.svg"
                ),
                event_date = "2022-04-20T08:00:00+00:00",
                event_timestamp = 1650441600,
                firstHalfStart = "1650441600",
                secondHalfStart = null,
                round = "Regular Season - 19",
                status = "First Half",
                statusShort = "1H",
                elapsed = 33,
                venue = "UMT Kylešovice",
                referee = null,
                homeTeam = LiveFixHomeTeam(),
                awayTeam = LiveFixAwayTeam(),
                goalsHomeTeam = "0",
                goalsAwayTeam = "1",
                score = LiveFixScore(
                    halftime = "0-1",
                    fulltime = null,
                    extratime = null,
                    penalty = null
                ),
                events = listOf(
                    LiveFixEvents(
                        elapsed = 24,
                        elapsed_plus = null,
                        team_id = 7970,
                        teamName = "Viktoria Plzeň U19",
                        player_id = null,
                        player = "",
                        assist_id = null,
                        assist = null,
                        type = "Goal",
                        detail = "Normal Goal",
                        comments = null
                    )
                )
            )
        )
    )
)