package com.android.chandchand.presentation.model

import android.content.Context
import com.android.chandchand.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LeaguesTitleList @Inject constructor(@ApplicationContext context: Context) {

    val leagues =
        listOf(
            LeagueTitleModel(
                context.getString(R.string.persian_gulf_cup),
                R.drawable.ic_persian_gulf_cup_32,
                923
            ),
            LeagueTitleModel(
                context.getString(R.string.english_premier_league),
                R.drawable.ic_premier_league_32,
                2790
            ),
            LeagueTitleModel(
                context.getString(R.string.spanish_la_liga),
                R.drawable.ic_la_liga_32,
                2833
            ),
            LeagueTitleModel(
                context.getString(R.string.italian_serie_a),
                R.drawable.ic_serie_a_32,
                2857
            ),
            LeagueTitleModel(
                context.getString(R.string.german_bundesliga_1),
                R.drawable.ic_bundesliga_32,
                2795
            ),
            LeagueTitleModel(
                context.getString(R.string.french_ligue_1),
                R.drawable.ic_ligue_1_32,
                2664
            )
        )
}