package com.example.parsing.model

import com.example.parsing.entity.ImageEntity
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("search?limit=10")
    suspend fun loadImages(): List<ImageEntity>
}