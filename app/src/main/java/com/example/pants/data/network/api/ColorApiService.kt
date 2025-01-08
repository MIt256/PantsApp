package com.example.pants.data.network.api

import com.example.pants.domain.model.ColorResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ColorApiService {
    @GET("id")
    suspend fun getColor(
        @Query("hsv") hsv: String
    ): ColorResponse
}
