package com.android.chandchand.fake

import com.android.data.fixtures.entity.*
import com.anonymous.data.mapper.FixtureServerEntityMapper
import com.anonymous.data.mapper.LiveFixEventsServerEntityMapper
import com.anonymous.data.mapper.LiveFixtureServerEntityMapper
import com.anonymous.common.result.Result
import com.anonymous.data.model.FixtureEntity
import com.anonymous.data.model.LiveFixtureEntities
import com.anonymous.data.model.LiveFixtureEntity
import com.anonymous.data.repository.FixturesRepository

class FakeFixturesRepository : com.anonymous.data.repository.FixturesRepository {

    private val fixturesMapper = com.anonymous.data.mapper.FixtureServerEntityMapper()
    private val liveFixturesMapper =
        com.anonymous.data.mapper.LiveFixtureServerEntityMapper(com.anonymous.data.mapper.LiveFixEventsServerEntityMapper())

    override suspend fun getFixtures(date: String): com.anonymous.common.result.Result<List<com.anonymous.data.model.FixtureEntity>> {
        val fixtureEntityList: List<com.anonymous.data.model.FixtureEntity> =
            fakeServerFixtures.api.fixtures.map { fixFixture ->
                fixturesMapper.map(fixFixture)
            }
        return com.anonymous.common.result.Result.Success(fixtureEntityList)
    }


    override suspend fun getLiveFixtures(): com.anonymous.common.result.Result<com.anonymous.data.model.LiveFixtureEntities> {
        val liveFixtureEntityList: List<com.anonymous.data.model.LiveFixtureEntity> =
            fakeLiveServerFixtures.api.fixtures.map { liveFixFixtures ->
                liveFixturesMapper.map(liveFixFixtures)
            }

        return com.anonymous.common.result.Result.Success(
            com.anonymous.data.model.LiveFixtureEntities(
                fakeLiveServerFixtures.api.results,
                liveFixtureEntityList
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