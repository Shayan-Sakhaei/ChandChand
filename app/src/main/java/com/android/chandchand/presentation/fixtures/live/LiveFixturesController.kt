package com.android.chandchand.presentation.fixtures.live

import com.airbnb.epoxy.TypedEpoxyController
import com.android.chandchand.presentation.common.LeagueFixturesClickListener
import com.android.chandchand.presentation.fixtures.fixtureBodyView
import com.android.chandchand.presentation.fixtures.fixtureHeaderView
import com.android.chandchand.presentation.model.LiveFixturesPerLeagueModels

class LiveFixturesController(
    private val leagueFixturesClickListener: LeagueFixturesClickListener
) : TypedEpoxyController<LiveFixturesPerLeagueModels>() {

    override fun buildModels(contanier: LiveFixturesPerLeagueModels?) {
        if (contanier?.results == 0) {
            //TODO Show empty state
        } else {
            contanier?.entities?.forEachIndexed { index, liveFixtureModel ->
                fixtureHeaderView {
                    id("live_header", index.toLong())
                    leagueModel(liveFixtureModel.leagueModel)
                    onHeaderExpanded { model, _, _, _ ->
                        model.leagueModel().let { league ->
                            leagueFixturesClickListener.onHeaderClicked(league)
                        }
                    }
                }
                if (liveFixtureModel.leagueModel.isExpanded) {
                    liveFixtureModel.fixtures.forEach { fixtureEntity ->
                        fixtureBodyView {
                            id(fixtureEntity.id)
                            homeLogoUrl(fixtureEntity.home_team_logo)
                            awayLogoUrl(fixtureEntity.away_team_logo)
                            homeName(fixtureEntity.home_team_name)
                            awayName(fixtureEntity.away_team_name)
                            homeGoals(fixtureEntity.goals_home)
                            awayGoals(fixtureEntity.goals_away)
                            status(fixtureEntity.status_short)
                            time(fixtureEntity.timestamp)
                        }
                    }
                }
            }
        }
    }
}
