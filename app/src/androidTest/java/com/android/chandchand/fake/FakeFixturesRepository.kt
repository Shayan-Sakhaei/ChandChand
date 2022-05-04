package com.android.chandchand.fake

import com.android.chandchand.data.common.Result
import com.android.chandchand.data.fixtures.entity.*
import com.android.chandchand.data.fixtures.mapper.FixtureServerEntityMapper
import com.android.chandchand.data.fixtures.mapper.LiveFixtureServerEntityMapper
import com.android.chandchand.domain.entities.FixtureEntity
import com.android.chandchand.domain.entities.LiveFixtureEntities
import com.android.chandchand.domain.entities.LiveFixtureEntity
import com.android.chandchand.domain.repositories.FixturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeFixturesRepository @Inject constructor(
) : FixturesRepository {

    private val fixturesMapper = FixtureServerEntityMapper()
    private val liveFixturesMapper = LiveFixtureServerEntityMapper()

    override fun getFixtures(date: String): Flow<Result<List<FixtureEntity>>> =
        flow {
            val fixtureEntityList: List<FixtureEntity> =
                fakeServerFixtures.api.fixtures.map { fixFixture ->
                    fixturesMapper.map(fixFixture)
                }
            emit(Result.Success(fixtureEntityList))
        }

    override fun getLiveFixtures(): Flow<Result<LiveFixtureEntities>> =
        flow {
            val liveFixtureEntityList: List<LiveFixtureEntity> =
                fakeLiveServerFixtures.api.fixtures.map { liveFixFixtures ->
                    liveFixturesMapper.map(liveFixFixtures)
                }

            emit(
                Result.Success(
                    LiveFixtureEntities(
                        fakeLiveServerFixtures.api.results,
                        liveFixtureEntityList
                    )
                )
            )
        }
}

private val fakeServerFixtures = FixturesServerModel(
    FixApi(
        results = 1,
        fixtures = listOf(
            FixFixtures(
                fixture_id = 844125,
                league_id = 3030,
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
                homeTeam = FixHomeTeam(
                    2742,
                    "Persepolis FC",
                    "https://media.api-sports.io/football/teams/2742.png"
                ),
                awayTeam = FixAwayTeam(
                    2733,
                    "Esteghlal FC",
                    "https://media.api-sports.io/football/teams/2733.png"
                ),
                goalsHomeTeam = "1",
                goalsAwayTeam = "1",
                score = FixScore()
            )
        )
    )
)


private val fakeLiveServerFixtures = LiveFixturesServerModel(
    LiveFixApi(
        results = 1,
        fixtures = listOf(
            LiveFixFixtures(
                fixture_id = 844125,
                league_id = 3030,
                league = LiveFixLeague(),
                event_date = "2022-03-17T14:00:00+00:00",
                event_timestamp = 1647525600,
                firstHalfStart = "1647525600",
                secondHalfStart = "1647529200",
                round = "Regular Season - 23",
                status = "Second Half",
                statusShort = "2H",
                elapsed = 77,
                venue = "Azadi Stadium",
                referee = "M. Seyedali",
                homeTeam = LiveFixHomeTeam(
                    2742,
                    "Persepolis FC",
                    "https://media.api-sports.io/football/teams/2742.png"
                ),
                awayTeam = LiveFixAwayTeam(
                    2733,
                    "Esteghlal FC",
                    "https://media.api-sports.io/football/teams/2733.png"
                ),
                goalsHomeTeam = "1",
                goalsAwayTeam = "0",
                score = LiveFixScore(
                    halftime = "1-0",
                    fulltime = null,
                    extratime = null,
                    penalty = null
                ),
                events = listOf(
                    LiveFixEvents(
                        elapsed = 40,
                        elapsed_plus = null,
                        team_id = 2742,
                        teamName = "Persepolis FC",
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