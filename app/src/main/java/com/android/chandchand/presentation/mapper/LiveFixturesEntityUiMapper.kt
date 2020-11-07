package com.android.chandchand.presentation.mapper

import com.android.chandchand.R
import com.android.chandchand.data.common.Mapper
import com.android.chandchand.domain.entities.LiveFixtureEntities
import com.android.chandchand.domain.entities.LiveFixtureEntity
import com.android.chandchand.presentation.model.LeagueModel
import com.android.chandchand.presentation.model.LiveFixturesPerLeagueModel
import com.android.chandchand.presentation.model.LiveFixturesPerLeagueModels
import javax.inject.Inject

class LiveFixtureEntityUiMapper @Inject constructor() :
    Mapper<LiveFixtureEntities, LiveFixturesPerLeagueModels> {

    override fun map(item: LiveFixtureEntities): LiveFixturesPerLeagueModels {

        val persianGulfCup = ArrayList<LiveFixtureEntity>()
        val englishPremierLeague = ArrayList<LiveFixtureEntity>()
        val spanishLaLiga = ArrayList<LiveFixtureEntity>()
        val italianSerieA = ArrayList<LiveFixtureEntity>()
        val germanBundesliga1 = ArrayList<LiveFixtureEntity>()
        val frenchLigue1 = ArrayList<LiveFixtureEntity>()

        item.entities.map { fixtureEntity ->
            when (fixtureEntity.league_id) {
                923 -> {
                    persianGulfCup.add(fixtureEntity)
                }
                2790 -> {
                    englishPremierLeague.add(fixtureEntity)
                }
                2833 -> {
                    spanishLaLiga.add(fixtureEntity)
                }
                2857 -> {
                    italianSerieA.add(fixtureEntity)
                }
                2755 -> {
                    germanBundesliga1.add(fixtureEntity)
                }
                2664 -> {
                    frenchLigue1.add(fixtureEntity)
                }
                else -> {
                }
            }
        }
        return LiveFixturesPerLeagueModels(
            item.results,
            listOf(
                LiveFixturesPerLeagueModel(
                    LeagueModel(
                        R.drawable.ic_persian_gulf_cup_32,
                        R.string.persian_gulf_cup,
                        persianGulfCup.size
                    ),
                    persianGulfCup
                ),
                LiveFixturesPerLeagueModel(
                    LeagueModel(
                        R.drawable.ic_premier_league_32,
                        R.string.english_premier_league,
                        englishPremierLeague.size
                    ),
                    englishPremierLeague
                ),
                LiveFixturesPerLeagueModel(
                    LeagueModel(
                        R.drawable.ic_la_liga_32,
                        R.string.spanish_la_liga,
                        spanishLaLiga.size
                    ),
                    spanishLaLiga
                ),
                LiveFixturesPerLeagueModel(
                    LeagueModel(
                        R.drawable.ic_serie_a_32,
                        R.string.italian_serie_a,
                        italianSerieA.size
                    ),
                    italianSerieA
                ),
                LiveFixturesPerLeagueModel(
                    LeagueModel(
                        R.drawable.ic_bundesliga_32,
                        R.string.german_bundesliga_1,
                        germanBundesliga1.size
                    ),
                    germanBundesliga1
                ),
                LiveFixturesPerLeagueModel(
                    LeagueModel(
                        R.drawable.ic_ligue_1_32,
                        R.string.french_ligue_1,
                        frenchLigue1.size
                    ),
                    frenchLigue1
                )
            )
        )
    }
}
