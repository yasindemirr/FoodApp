package com.demir.foodapp.ui.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object{
        private val BASE_URL="https://www.themealdb.com/api/json/v1/1/"
        val api= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(FoodApi::class.java)
    }

}