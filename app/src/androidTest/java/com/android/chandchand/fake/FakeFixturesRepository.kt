package com.android.chandchand.fake

import com.android.data.common.Result
import com.android.chandchand.data.fixtures.entity.*
import com.android.data.fixtures.mapper.FixtureServerEntityMapper
import com.android.data.fixtures.mapper.LiveFixtureServerEntityMapper
import com.android.domain.entities.FixtureEntity
import com.android.domain.entities.LiveFixtureEntities
import com.android.domain.entities.LiveFixtureEntity
import com.android.domain.repositories.FixturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeFixturesRepository @Inject constructor(
) : com.android.domain.repositories.FixturesRepository {

    private val fixturesMapper = com.android.data.fixtures.mapper.FixtureServerEntityMapper()
    private val liveFixturesMapper =
        com.android.data.fixtures.mapper.LiveFixtureServerEntityMapper()

    override fun getFixtures(date: String): Flow<com.android.data.common.Result<List<com.android.domain.entities.FixtureEntity>>> =
        flow {
            val fixtureEntityList: List<com.android.domain.entities.FixtureEntity> =
                fakeServerFixtures.api.fixtures.map { fixFixture ->
                    fixturesMapper.map(fixFixture)
                }
            emit(com.android.data.common.Result.Success(fixtureEntityList))
        }

    override fun getLiveFixtures(): Flow<com.android.data.common.Result<com.android.domain.entities.LiveFixtureEntities>> =
        flow {
            val liveFixtureEntityList: List<com.android.domain.entities.LiveFixtureEntity> =
                fakeLiveServerFixtures.api.fixtures.map { liveFixFixtures ->
                    liveFixturesMapper.map(liveFixFixtures)
                }

            emit(
                com.android.data.common.Result.Success(
                    com.android.domain.entities.LiveFixtureEntities(
                        fakeLiveServerFixtures.api.results,
                        liveFixtureEntityList
                    )
                )
            )
        }
}

private val fakeServerFixtures = com.android.data.fixtures.entity.FixturesServerModel(
    com.android.data.fixtures.entity.FixApi(
        results = 1,
        fixtures = listOf(
            com.android.data.fixtures.entity.FixFixtures(
                fixture_id = 844125,
                league_id = 3030,
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
                homeTeam = com.android.data.fixtures.entity.FixHomeTeam(
                    2742,
                    "Persepolis FC",
                    "https://media.api-sports.io/football/teams/2742.png"
                ),
                awayTeam = com.android.data.fixtures.entity.FixAwayTeam(
                    2733,
                    "Esteghlal FC",
                    "https://media.api-sports.io/football/teams/2733.png"
                ),
                goalsHomeTeam = "1",
                goalsAwayTeam = "1",
                score = com.android.data.fixtures.entity.FixScore()
            )
        )
    )
)


private val fakeLiveServerFixtures = com.android.data.fixtures.entity.LiveFixturesServerModel(
    com.android.data.fixtures.entity.LiveFixApi(
        results = 1,
        fixtures = listOf(
            com.android.data.fixtures.entity.LiveFixFixtures(
                fixture_id = 844125,
                league_id = 3030,
                league = com.android.data.fixtures.entity.LiveFixLeague(),
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
                homeTeam = com.android.data.fixtures.entity.LiveFixHomeTeam(
                    2742,
                    "Persepolis FC",
                    "https://media.api-sports.io/football/teams/2742.png"
                ),
                awayTeam = com.android.data.fixtures.entity.LiveFixAwayTeam(
                    2733,
                    "Esteghlal FC",
                    "https://media.api-sports.io/football/teams/2733.png"
                ),
                goalsHomeTeam = "1",
                goalsAwayTeam = "0",
                score = com.android.data.fixtures.entity.LiveFixScore(
                    halftime = "1-0",
                    fulltime = null,
                    extratime = null,
                    penalty = null
                ),
                events = listOf(
                    com.android.data.fixtures.entity.LiveFixEvents(
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