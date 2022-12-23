package com.android.chandchand.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.anonymous.ui.navigation.ChandChandNavigationDestination

data class TopLevelDestination(
    override val route: String,
    override val destination: String,
    @DrawableRes val selectedIconId: Int,
    @DrawableRes val unselectedIconId: Int,
    @StringRes val iconTextId: Int
) : ChandChandNavigationDestination