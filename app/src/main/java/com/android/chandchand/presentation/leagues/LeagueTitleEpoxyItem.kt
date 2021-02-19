package com.android.chandchand.presentation.leagues

import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.android.chandchand.R
import com.android.chandchand.presentation.model.LeagueTitleModel
import com.android.chandchand.presentation.utils.KotlinModel
import com.google.android.material.card.MaterialCardView

data class LeagueTitleEpoxyItem(
    val item: LeagueTitleModel
) : KotlinModel(R.layout.league_title_view) {

    private val root by bind<MaterialCardView>(R.id.league_title_view_root)
    private val logo by bind<ImageView>(R.id.iv_league_logo)
    private val title by bind<TextView>(R.id.tv_league_title)

    override fun bind() {
        ViewCompat.setTransitionName(root, item.name)
        logo.setImageResource(item.logo)
        title.text = item.name
        root.setOnClickListener {
            selectedLeague?.invoke(root, item)
        }
    }

    private var selectedLeague: ((MaterialCardView, LeagueTitleModel) -> Unit)? = null

    fun setSelectedLeagueCallback(call: (MaterialCardView, LeagueTitleModel) -> Unit): LeagueTitleEpoxyItem {
        selectedLeague = call
        return this
    }
}