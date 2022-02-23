package com.example.composetest.network

import com.example.composetest.models.NameX
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("random_name")
    suspend fun getRandomUser() : NameX
}