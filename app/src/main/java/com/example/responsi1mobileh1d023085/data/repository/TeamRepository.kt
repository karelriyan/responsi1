package com.example.responsi1mobileh1d023085.data.repository

import com.example.responsi1mobileh1d023085.data.network.RetrofitInstance
import com.example.responsi1mobileh1d023085.utils.Constants

class TeamRepository {
    suspend fun fetchTeam(teamId: Int) =
        RetrofitInstance.api.getTeam(teamId, Constants.API_TOKEN)
}