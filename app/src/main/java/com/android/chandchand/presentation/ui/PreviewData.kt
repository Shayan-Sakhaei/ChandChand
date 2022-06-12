package com.android.chandchand.presentation.ui

import com.android.chandchand.R
import com.android.chandchand.data.fixtures.entity.*
import com.android.data.fixtures.mapper.FixtureServerEntityMapper
import com.android.data.fixtures.mapper.LiveFixtureServerEntityMapper
import com.android.domain.entities.StandingEntity
import com.android.chandchand.presentation.model.FixturesPerLeagueModel
import com.android.chandchand.presentation.model.LeagueModel
import com.android.chandchand.presentation.model.LiveFixturesPerLeagueModel


object PreviewData {
    private val fixturesMapper = com.android.data.fixtures.mapper.FixtureServerEntityMapper()
    private val fixFixture =
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

    val fixture = fixturesMapper.map(fixFixture)
    val fixturesPerLeague = FixturesPerLeagueModel(
        LeagueModel(R.drawable.ic_persian_gulf_cup_32, R.string.persian_gulf_cup, 3),
        listOf(fixture, fixture, fixture),
        true
    )


    private val liveFixturesMapper =
        com.android.data.fixtures.mapper.LiveFixtureServerEntityMapper()
    private val liveFixFixture =
        com.android.data.fixtures.entity.LiveFixFixtures(
            fixture_id = 844125,
            league_id = 3030,
            league = com.android.data.fixtures.entity.LiveFixLeague(),
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
            goalsAwayTeam = "1",
            score = com.android.data.fixtures.entity.LiveFixScore(),
            events = emptyList()
        )

    private val liveFixture = liveFixturesMapper.map(liveFixFixture)
    val liveFixturesPerLeague = LiveFixturesPerLeagueModel(
        LeagueModel(R.drawable.ic_persian_gulf_cup_32, R.string.persian_gulf_cup, 3),
        listOf(liveFixture, liveFixture, liveFixture),
        true
    )


    val standing = com.android.domain.entities.StandingEntity(
        1,
        2742,
        "Persepolis FC",
        "https://media.api-sports.io/football/teams/2742.png",
        null,
        "WWWWD",
        "same",
        null,
        30,
        19,
        10,
        1,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        33,
        67,
        null
    )
}