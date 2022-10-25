package com.android.chandchand.presentation.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TopLevelDestination(
    override val route: String,
    override val destination: String,
    @DrawableRes val selectedIconId: Int,
    @DrawableRes val unselectedIconId: Int,
    @StringRes val iconTextId: Int
) : ChandChandNavigationDestination