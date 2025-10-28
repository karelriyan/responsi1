package com.example.responsi1mobileh1d023085.data.model

data class TeamResponse (
    val id: Int?,
    val name: String?,
    val shortName: String?,
    val tla: String?,
    val crest: String?,
    val address: String?,
    val website: String?,
    val founded: Int?,
    val clubColors: String?,
    val venue: String?,
    val coach: CoachResponse?,
    val squad: List<PlayerResponse> = emptyList()
)