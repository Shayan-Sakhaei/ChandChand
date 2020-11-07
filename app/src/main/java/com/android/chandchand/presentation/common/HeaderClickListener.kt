package com.android.chandchand.presentation.common

import com.android.chandchand.presentation.model.LeagueModel

interface HeaderClickListener {
    fun onHeaderClicked(leagueModel: LeagueModel)
}