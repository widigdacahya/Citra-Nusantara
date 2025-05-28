package com.example.citranusantaraandroid.networking

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


// List of available API endpoint
interface ApiService {

    @GET("{categoryPath}")
    suspend fun getCategoryItems(
        @Path("categoryPath") categoryPath: String
    ) : Response<JsonElement>

}