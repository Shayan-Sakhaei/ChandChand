package com.android.chandchand.presentation.common

interface IView<S : IState> {
    fun render(state: S)
}