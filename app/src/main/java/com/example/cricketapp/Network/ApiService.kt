package com.example.cricketapp.Network

import com.example.cricketapp.Data.MatchDetailsModel
import com.example.cricketapp.Data.Squad
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("nzin01312019187360.json")
    suspend fun getMatchDetails(): MatchDetailsModel

    @GET("sapk01222019186652.json")
    suspend fun getMatchDetails1(): MatchDetailsModel

    @GET("nzin01312019187360.json")
    fun fetchData(): Call<JsonObject>

    @GET("sapk01222019186652.json")
    fun fetchData1(): Call<JsonObject>
}

    object RetrofitClient {
        private const val BASE_URL = "https://demo.sportz.io/"

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService: ApiService = retrofit.create(ApiService::class.java)
}