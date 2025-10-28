package com.example.responsi1mobileh1d023085.data.network

import com.example.responsi1mobileh1d023085.data.model.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FootballApi {
    @GET("teams/{id}")
    suspend fun getTeam(
        @Path("id") teamId: Int,
        @Header("X-Auth-Token") token: String
    ): Response<TeamResponse>
}