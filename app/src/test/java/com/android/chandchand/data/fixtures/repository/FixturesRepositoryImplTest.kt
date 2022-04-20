package com.android.chandchand.data.fixtures.repository

import com.android.chandchand.data.common.Result
import com.android.chandchand.data.fixtures.datasource.FakeFixturesDataSource
import com.android.chandchand.data.fixtures.entity.*
import com.android.chandchand.data.fixtures.mapper.FixtureServerEntityMapper
import com.android.chandchand.data.fixtures.mapper.LiveFixtureServerEntityMapper
import com.android.chandchand.domain.datasources.FixturesDataSource
import com.android.chandchand.domain.entities.FixtureEntity
import com.android.chandchand.domain.entities.LiveFixtureEntities
import com.android.chandchand.domain.entities.LiveFixtureEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FixturesRepositoryImplTest {

    private lateinit var dataSource: FixturesDataSource
    private lateinit var fixturesMapper: FixtureServerEntityMapper
    private lateinit var liveFixturesMapper: LiveFixtureServerEntityMapper
    private lateinit var repository: FixturesRepositoryImpl

    @Before
    fun setUp() {
        fixturesMapper = FixtureServerEntityMapper()
        liveFixturesMapper = LiveFixtureServerEntityMapper()
    }

    @Test
    fun `getFixtures Happy Path`() = runBlockingTest {
        dataSource = FakeFixturesDataSource(fixturesServerModel = fakeServerFixtures)
        repository = FixturesRepositoryImpl(dataSource, fixturesMapper, liveFixturesMapper)

        val states = repository.getFixtures("2022-03-17").toList()

        val fixtureEntityList: List<FixtureEntity> =
            fakeServerFixtures.api.fixtures.map { fixFixture ->
                fixturesMapper.map(fixFixture)
            }

        val assertions = listOf(Result.Success(fixtureEntityList))

        Assert.assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            Assert.assertEquals(assertion, state)
        }
    }


    @Test
    fun `getFixtures Unhappy Path`() = runBlockingTest {
        dataSource = FakeFixturesDataSource()
        repository = FixturesRepositoryImpl(dataSource, fixturesMapper, liveFixturesMapper)

        val states = repository.getFixtures("2022-03-17").toList()

        val assertions = listOf(Result.Error(""))

        Assert.assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            Assert.assertEquals(assertion, state)
        }
    }


    @Test
    fun `getLiveFixtures Happy Path`() = runBlockingTest {
        dataSource = FakeFixturesDataSource(liveFixturesServerModel = fakeLiveServerFixtures)
        repository = FixturesRepositoryImpl(dataSource, fixturesMapper, liveFixturesMapper)

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

        Assert.assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            Assert.assertEquals(assertion, state)
        }
    }


    @Test
    fun `getLiveFixtures Unhappy Path`() = runBlockingTest {
        dataSource = FakeFixturesDataSource()
        repository = FixturesRepositoryImpl(dataSource, fixturesMapper, liveFixturesMapper)

        val states = repository.getLiveFixtures().toList()

        val assertions = listOf(Result.Error(""))

        Assert.assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            Assert.assertEquals(assertion, state)
        }
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