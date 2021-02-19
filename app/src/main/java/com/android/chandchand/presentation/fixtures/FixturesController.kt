package com.android.chandchand.presentation.fixtures

import com.airbnb.epoxy.TypedEpoxyController
import com.android.chandchand.presentation.common.LeagueFixturesClickListener
import com.android.chandchand.presentation.model.FixturesPerLeagueModel

class FixturesController(
    private val leagueFixturesClickListener: LeagueFixturesClickListener
) : TypedEpoxyController<List<FixturesPerLeagueModel>>() {

    override fun buildModels(container: List<FixturesPerLeagueModel>?) {
        container?.forEachIndexed { index, fixtureModel ->
            fixtureHeaderView {
                id("header", index.toLong())
                leagueModel(fixtureModel.leagueModel)
                onHeaderExpanded { model, _, _, _ ->
                    model.leagueModel().let { league ->
                        leagueFixturesClickListener.onHeaderClicked(league)
                    }
                }
            }
            if (fixtureModel.leagueModel.isExpanded) {
                fixtureModel.fixtures.forEach { fixtureEntity ->
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
                        onPredictionClicked { _, _, _, _ ->
                            leagueFixturesClickListener.onPredictionClicked(
                                fixtureEntity.id,
                                fixtureEntity.home_team_logo,
                                fixtureEntity.away_team_logo
                            )
                        }
                    }
                }
            }
        }
    }
}