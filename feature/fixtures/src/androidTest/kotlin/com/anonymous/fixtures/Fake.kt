package com.anonymous.fixtures

import com.anonymous.data.mapper.FixtureServerEntityMapper
import com.anonymous.fixtures.model.FixturesPerLeagueModel
import com.anonymous.fixtures.model.LeagueHeaderModel
import com.anonymous.network.model.*

object Fake {

    private val fixturesMapper = FixtureServerEntityMapper()

    private val persianGulfCupFixFixture =
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
            goalsHomeTeam = "4",
            goalsAwayTeam = "2",
            score = FixScore()
        )

    private val premierLeagueFixFixture =
        FixFixtures(
            fixture_id = 710934,
            league_id = 3456,
            league = FixLeague(),
            event_date = "2022-05-22T19:30:00+04:30",
            event_timestamp = 1653231600,
            firstHalfStart = "1653231600",
            secondHalfStart = "1653235200",
            round = "Regular Season - 38",
            status = "Match Finished",
            statusShort = "FT",
            elapsed = 90,
            venue = "Etihad Stadium",
            referee = "M. Oliver",
            homeTeam = FixHomeTeam(
                50,
                "Manchester City",
                "https://media.api-sports.io/football/teams/50.png"
            ),
            awayTeam = FixAwayTeam(
                66,
                "Aston Villa",
                "https://media.api-sports.io/football/teams/66.png"
            ),
            goalsHomeTeam = "3",
            goalsAwayTeam = "2",
            score = FixScore()
        )

    val persianGulfCupFixture = fixturesMapper.map(persianGulfCupFixFixture)
    private val premierLeagueFixture = fixturesMapper.map(premierLeagueFixFixture)

    val fixturesPerLeague = listOf(
        FixturesPerLeagueModel(
            LeagueHeaderModel(
                R.drawable.ic_persian_gulf_cup_32,
                R.string.persian_gulf_cup,
                1
            ),
            listOf(persianGulfCupFixture),
            true
        ),
        FixturesPerLeagueModel(
            LeagueHeaderModel(
                R.drawable.ic_premier_league_32,
                R.string.english_premier_league,
                1
            ),
            listOf(premierLeagueFixture),
            true
        )
    )
}