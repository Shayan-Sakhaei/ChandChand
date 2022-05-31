package com.android.chandchand.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LeagueTitleModel(
    @StringRes val titleResId: Int,
    @DrawableRes val logoResId: Int,
    val id: Int
)