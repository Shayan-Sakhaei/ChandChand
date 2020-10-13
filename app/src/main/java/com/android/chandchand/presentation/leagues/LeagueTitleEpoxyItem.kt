package com.android.chandchand.presentation.leagues

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.android.chandchand.R
import com.android.chandchand.presentation.model.LeagueTitleModel
import com.android.chandchand.presentation.utils.KotlinModel

data class LeagueTitleEpoxyItem(
    val item: LeagueTitleModel
) : KotlinModel(R.layout.league_title_view) {

    private val root by bind<View>(R.id.league_title_view_root)
    private val logo by bind<ImageView>(R.id.iv_league_logo)
    private val title by bind<TextView>(R.id.tv_league_title)

    override fun bind() {
        logo.setImageResource(item.logo)
        title.text = item.name
        root.setOnClickListener {
            selectedLeague?.invoke(item)
        }
    }

    private var selectedLeague: ((LeagueTitleModel) -> Unit)? = null

    fun setSelectedLeagueCallback(call: (LeagueTitleModel) -> Unit): LeagueTitleEpoxyItem {
        selectedLeague = call
        return this
    }
}