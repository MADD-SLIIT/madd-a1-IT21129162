package com.example.spotterapp.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // Use 10.0.2.2 instead of localhost to refer to your computer from the emulator
    private const val BASE_URL = "http://10.0.2.2:5000"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}


