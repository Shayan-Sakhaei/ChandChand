package com.android.chandchand.presentation.mapper

import com.android.chandchand.R
import com.android.data.common.Mapper
import com.android.domain.entities.FixtureEntity
import com.android.chandchand.presentation.model.FixturesPerLeagueModel
import com.android.chandchand.presentation.model.LeagueModel
import javax.inject.Inject

class FixtureEntityUiMapper @Inject constructor() :
    com.android.data.common.Mapper<List<com.android.domain.entities.FixtureEntity>, List<FixturesPerLeagueModel>> {

    override fun map(item: List<com.android.domain.entities.FixtureEntity>): List<FixturesPerLeagueModel> {

        val persianGulfCup = ArrayList<com.android.domain.entities.FixtureEntity>()
        val englishPremierLeague = ArrayList<com.android.domain.entities.FixtureEntity>()
        val spanishLaLiga = ArrayList<com.android.domain.entities.FixtureEntity>()
        val italianSerieA = ArrayList<com.android.domain.entities.FixtureEntity>()
        val germanBundesliga1 = ArrayList<com.android.domain.entities.FixtureEntity>()
        val frenchLigue1 = ArrayList<com.android.domain.entities.FixtureEntity>()

        item.map { fixtureEntity ->
            when (fixtureEntity.league_id) {
                3935 -> {
                    persianGulfCup.add(fixtureEntity)
                }
                3456 -> {
                    englishPremierLeague.add(fixtureEntity)
                }
                3513 -> {
                    spanishLaLiga.add(fixtureEntity)
                }
                3576 -> {
                    italianSerieA.add(fixtureEntity)
                }
                3510 -> {
                    germanBundesliga1.add(fixtureEntity)
                }
                3506 -> {
                    frenchLigue1.add(fixtureEntity)
                }
                else -> {
                }
            }
        }
        return listOf(
            FixturesPerLeagueModel(
                LeagueModel(
                    R.drawable.ic_persian_gulf_cup_32,
                    R.string.persian_gulf_cup,
                    persianGulfCup.size
                ),
                persianGulfCup
            ),
            FixturesPerLeagueModel(
                LeagueModel(
                    R.drawable.ic_premier_league_32,
                    R.string.english_premier_league,
                    englishPremierLeague.size
                ),
                englishPremierLeague
            ),
            FixturesPerLeagueModel(
                LeagueModel(
                    R.drawable.ic_la_liga_32,
                    R.string.spanish_la_liga,
                    spanishLaLiga.size
                ),
                spanishLaLiga
            ),
            FixturesPerLeagueModel(
                LeagueModel(
                    R.drawable.ic_serie_a_32,
                    R.string.italian_serie_a,
                    italianSerieA.size
                ),
                italianSerieA
            ),
            FixturesPerLeagueModel(
                LeagueModel(
                    R.drawable.ic_bundesliga_32,
                    R.string.german_bundesliga_1,
                    germanBundesliga1.size
                ),
                germanBundesliga1
            ),
            FixturesPerLeagueModel(
                LeagueModel(
                    R.drawable.ic_ligue_1_32,
                    R.string.french_ligue_1,
                    frenchLigue1.size
                ),
                frenchLigue1
            )
        )
    }
}
