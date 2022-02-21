package com.example.composetest.network

import com.example.composetest.models.RandomUser
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/")
    suspend fun getRandomUser() : Response<RandomUser>
}