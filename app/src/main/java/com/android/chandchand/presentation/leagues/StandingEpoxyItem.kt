package com.android.chandchand.presentation.leagues

import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.chandchand.R
import com.android.chandchand.domain.entities.StandingEntity
import com.android.chandchand.presentation.utils.KotlinModel
import com.bumptech.glide.Glide

data class StandingEpoxyItem(
    val item: StandingEntity
) : KotlinModel(R.layout.standing_list_item) {

    private val root by bind<ConstraintLayout>(R.id.standing_list_item_root)
    private val points by bind<TextView>(R.id.tv_points)
    private val goalsDiff by bind<TextView>(R.id.tv_goals_diff)
    private val lose by bind<TextView>(R.id.tv_lose)
    private val draw by bind<TextView>(R.id.tv_draw)
    private val win by bind<TextView>(R.id.tv_win)
    private val match by bind<TextView>(R.id.tv_match)
    private val teamName by bind<TextView>(R.id.tv_team_name)
    private val teamLogo by bind<ImageView>(R.id.iv_team_logo)
    private val rank by bind<TextView>(R.id.tv_rank)

    override fun bind() {
        points.text = item.points.toString()
        goalsDiff.text = item.goalsDiff.toString()
        lose.text = item.allLose.toString()
        draw.text = item.allDraw.toString()
        win.text = item.allWin.toString()
        match.text = item.allMatchsPlayed.toString()
        teamName.text = item.teamName
        Glide.with(root.context)
            .load(item.logo)
            .placeholder(R.drawable.ic_flag_placeholder_32)
            .into(teamLogo)
        rank.text = item.rank.toString()
        root.setOnClickListener {
            selectedTeamId?.invoke(item.team_id)
        }
    }

    private var selectedTeamId: ((Int) -> Unit)? = null

    fun setSelectedTeamIdCallback(call: (Int) -> Unit): StandingEpoxyItem {
        selectedTeamId = call
        return this
    }

}