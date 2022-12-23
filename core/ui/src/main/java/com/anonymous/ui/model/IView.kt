package com.anonymous.ui.model

interface IView<S : IState> {
    fun render(state: S)
}