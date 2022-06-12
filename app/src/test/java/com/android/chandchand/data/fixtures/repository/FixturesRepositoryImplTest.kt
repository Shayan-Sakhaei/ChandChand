package com.android.chandchand.data.fixtures.repository

import com.android.data.common.Result
import com.android.chandchand.data.fixtures.datasource.FakeFixturesDataSource
import com.android.data.fixtures.datasource.RemoteFixturesDataSource
import com.android.chandchand.data.fixtures.entity.*
import com.android.data.fixtures.mapper.FixtureServerEntityMapper
import com.android.data.fixtures.mapper.LiveFixtureServerEntityMapper
import com.android.data.datasources.FixturesDataSource
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

    private lateinit var dataSource: com.android.data.datasources.FixturesDataSource
    private lateinit var fixturesMapper: com.android.data.fixtures.mapper.FixtureServerEntityMapper
    private lateinit var liveFixturesMapper: com.android.data.fixtures.mapper.LiveFixtureServerEntityMapper
    private lateinit var repository: com.android.data.fixtures.repository.FixturesRepositoryImpl

    @Before
    fun setUp() {
        fixturesMapper = com.android.data.fixtures.mapper.FixtureServerEntityMapper()
        liveFixturesMapper = com.android.data.fixtures.mapper.LiveFixtureServerEntityMapper()
    }

    @Test
    fun `getFixtures Happy Path`() = runBlockingTest {
        dataSource = FakeFixturesDataSource(fixturesServerModel = fakeServerFixtures)
        repository = com.android.data.fixtures.repository.FixturesRepositoryImpl(
            dataSource,
            fixturesMapper,
            liveFixturesMapper
        )

        val states = repository.getFixtures("2022-03-17").toList()

        val fixtureEntityList: List<com.android.domain.entities.FixtureEntity> =
            fakeServerFixtures.api.fixtures.map { fixFixture ->
                fixturesMapper.map(fixFixture)
            }

        val assertions = listOf(com.android.data.common.Result.Success(fixtureEntityList))

        assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            assertEquals(assertion, state)
        }
    }


    @Test
    fun `getFixtures Unhappy Path`() = runBlockingTest {
        dataSource = FakeFixturesDataSource()
        repository = com.android.data.fixtures.repository.FixturesRepositoryImpl(
            dataSource,
            fixturesMapper,
            liveFixturesMapper
        )

        val states = repository.getFixtures("2022-03-17").toList()

        val assertions = listOf(com.android.data.common.Result.Error(""))

        assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            assertEquals(assertion, state)
        }
    }


    @Test
    fun `getLiveFixtures Happy Path`() = runBlockingTest {
        dataSource = FakeFixturesDataSource(liveFixturesServerModel = fakeLiveServerFixtures)
        repository = com.android.data.fixtures.repository.FixturesRepositoryImpl(
            dataSource,
            fixturesMapper,
            liveFixturesMapper
        )

        val states = repository.getLiveFixtures().toList()

        val liveFixtureEntityList: List<com.android.domain.entities.LiveFixtureEntity> =
            fakeLiveServerFixtures.api.fixtures.map { liveFixFixtures ->
                liveFixturesMapper.map(liveFixFixtures)
            }

        val assertions = listOf(
            com.android.data.common.Result.Success(
                com.android.domain.entities.LiveFixtureEntities(
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
        repository = com.android.data.fixtures.repository.FixturesRepositoryImpl(
            dataSource,
            fixturesMapper,
            liveFixturesMapper
        )

        val states = repository.getLiveFixtures().toList()

        val assertions = listOf(com.android.data.common.Result.Error(""))

        assertEquals(assertions.size, states.size)
        assertions.zip(states) { assertion, state ->
            assertEquals(assertion, state)
        }
    }

    @Test
    fun `getFixtures should invoke FixturesDataSource getFixturesByDate`() = runBlockingTest {

        val fixtureEntityList: List<com.android.domain.entities.FixtureEntity> =
            fakeServerFixtures.api.fixtures.map { fixFixture ->
                fixturesMapper.map(fixFixture)
            }

        val expected = com.android.data.common.Result.Success(fixtureEntityList)

        dataSource = mockk<com.android.data.fixtures.datasource.RemoteFixturesDataSource>()
        coEvery { dataSource.getFixturesByDate("2022-03-17") } returns Response.success(
            fakeServerFixtures
        )

        repository = com.android.data.fixtures.repository.FixturesRepositoryImpl(
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

        val liveFixtureEntityList: List<com.android.domain.entities.LiveFixtureEntity> =
            fakeLiveServerFixtures.api.fixtures.map { liveFixFixtures ->
                liveFixturesMapper.map(liveFixFixtures)
            }

        val expected = com.android.data.common.Result.Success(
            com.android.domain.entities.LiveFixtureEntities(
                fakeLiveServerFixtures.api.results,
                liveFixtureEntityList
            )
        )

        dataSource = mockk<com.android.data.fixtures.datasource.RemoteFixturesDataSource>()
        coEvery { dataSource.getLiveFixtures() } returns Response.success(fakeLiveServerFixtures)

        repository = com.android.data.fixtures.repository.FixturesRepositoryImpl(
            dataSource,
            fixturesMapper,
            liveFixturesMapper
        )

        val actual = repository.getLiveFixtures().first()

        coVerify { dataSource.getLiveFixtures() }
        assertEquals(expected, actual)
    }
}

private val fakeServerFixtures = com.android.data.fixtures.entity.FixturesServerModel(
    com.android.data.fixtures.entity.FixApi(
        results = 130,
        fixtures = listOf(
            com.android.data.fixtures.entity.FixFixtures(
                fixture_id = 844125,
                league_id = 3935,
                league = com.android.data.fixtures.entity.FixLeague(),
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
                homeTeam = com.android.data.fixtures.entity.FixHomeTeam(),
                awayTeam = com.android.data.fixtures.entity.FixAwayTeam(),
                goalsHomeTeam = "1",
                goalsAwayTeam = "1",
                score = com.android.data.fixtures.entity.FixScore()
            )
        )
    )
)


private val fakeLiveServerFixtures = com.android.data.fixtures.entity.LiveFixturesServerModel(
    com.android.data.fixtures.entity.LiveFixApi(
        results = 2,
        fixtures = listOf(
            com.android.data.fixtures.entity.LiveFixFixtures(
                fixture_id = 823624,
                league_id = 3645,
                league = com.android.data.fixtures.entity.LiveFixLeague(
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
                homeTeam = com.android.data.fixtures.entity.LiveFixHomeTeam(),
                awayTeam = com.android.data.fixtures.entity.LiveFixAwayTeam(),
                goalsHomeTeam = "0",
                goalsAwayTeam = "1",
                score = com.android.data.fixtures.entity.LiveFixScore(
                    halftime = "0-1",
                    fulltime = null,
                    extratime = null,
                    penalty = null
                ),
                events = listOf(
                    com.android.data.fixtures.entity.LiveFixEvents(
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