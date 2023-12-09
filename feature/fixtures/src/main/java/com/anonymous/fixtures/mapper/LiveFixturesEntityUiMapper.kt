package com.anonymous.fixtures.mapper

import com.anonymous.common.mapper.Mapper
import com.anonymous.data.model.LiveFixtureEntities
import com.anonymous.data.model.LiveFixtureEntity
import com.anonymous.fixtures.R
import com.anonymous.fixtures.model.LeagueHeaderModel
import com.anonymous.fixtures.model.LiveFixturesPerLeagueModel
import com.anonymous.fixtures.model.LiveFixturesPerLeagueModels
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
                5505 -> {
                    persianGulfCup.add(fixtureEntity)
                }

                5267 -> {
                    englishPremierLeague.add(fixtureEntity)
                }

                5284 -> {
                    spanishLaLiga.add(fixtureEntity)
                }

                5367 -> {
                    italianSerieA.add(fixtureEntity)
                }

                5347 -> {
                    germanBundesliga1.add(fixtureEntity)
                }

                5322 -> {
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
                    LeagueHeaderModel(
                        R.drawable.ic_persian_gulf_cup_32,
                        R.string.persian_gulf_cup,
                        persianGulfCup.size
                    ),
                    persianGulfCup
                ),
                LiveFixturesPerLeagueModel(
                    LeagueHeaderModel(
                        R.drawable.ic_premier_league_32,
                        R.string.english_premier_league,
                        englishPremierLeague.size
                    ),
                    englishPremierLeague
                ),
                LiveFixturesPerLeagueModel(
                    LeagueHeaderModel(
                        R.drawable.ic_la_liga_32,
                        R.string.spanish_la_liga,
                        spanishLaLiga.size
                    ),
                    spanishLaLiga
                ),
                LiveFixturesPerLeagueModel(
                    LeagueHeaderModel(
                        R.drawable.ic_serie_a_32,
                        R.string.italian_serie_a,
                        italianSerieA.size
                    ),
                    italianSerieA
                ),
                LiveFixturesPerLeagueModel(
                    LeagueHeaderModel(
                        R.drawable.ic_bundesliga_32,
                        R.string.german_bundesliga_1,
                        germanBundesliga1.size
                    ),
                    germanBundesliga1
                ),
                LiveFixturesPerLeagueModel(
                    LeagueHeaderModel(
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
