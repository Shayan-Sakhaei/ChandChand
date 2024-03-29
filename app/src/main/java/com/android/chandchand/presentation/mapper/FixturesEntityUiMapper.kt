package com.android.chandchand.presentation.mapper

import com.android.chandchand.R
import com.android.chandchand.presentation.model.FixturesPerLeagueModel
import com.android.chandchand.presentation.model.LeagueModel
import com.android.domain.common.Mapper
import com.android.domain.entities.FixtureEntity
import javax.inject.Inject

class FixtureEntityUiMapper @Inject constructor() :
    Mapper<List<FixtureEntity>, List<FixturesPerLeagueModel>> {

    override fun map(item: List<FixtureEntity>): List<FixturesPerLeagueModel> {

        val persianGulfCup = ArrayList<FixtureEntity>()
        val englishPremierLeague = ArrayList<FixtureEntity>()
        val spanishLaLiga = ArrayList<FixtureEntity>()
        val italianSerieA = ArrayList<FixtureEntity>()
        val germanBundesliga1 = ArrayList<FixtureEntity>()
        val frenchLigue1 = ArrayList<FixtureEntity>()

        item.map { fixtureEntity ->
            when (fixtureEntity.league_id) {
                4640 -> {
                    persianGulfCup.add(fixtureEntity)
                }
                4335 -> {
                    englishPremierLeague.add(fixtureEntity)
                }
                4378 -> {
                    spanishLaLiga.add(fixtureEntity)
                }
                4399 -> {
                    italianSerieA.add(fixtureEntity)
                }
                4346 -> {
                    germanBundesliga1.add(fixtureEntity)
                }
                4347 -> {
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
