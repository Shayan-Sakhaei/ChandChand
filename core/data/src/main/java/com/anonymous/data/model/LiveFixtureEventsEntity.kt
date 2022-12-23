package com.anonymous.data.model

data class LiveFixtureEventsEntity(
    val elapsed: Int? = 0,
    val elapsed_plus: String? = "",
    val team_id: Int? = 0,
    val teamName: String? = "",
    val player_id: String? = "",
    val player: String? = "",
    val assist_id: String? = "",
    val assist: String? = "",
    val type: String? = "",
    val detail: String? = "",
    val comments: String? = ""
)