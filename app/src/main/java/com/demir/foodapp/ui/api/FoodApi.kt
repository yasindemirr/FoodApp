package com.demir.foodapp.ui.api

import com.demir.foodapp.ui.model.FoodCategory
import com.demir.foodapp.ui.model.FoodResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//https://www.themealdb.com/api/json/v1/1/random.php
interface FoodApi {
    @GET("random.php")
    suspend fun getRandomMeal():Response<FoodResponse>
    @GET("lookup.php?")
    suspend fun getRandomMealDetail(
        @Query("i") i:String):Response<FoodResponse>
    @GET("filter.php?")
    suspend fun getPopularMeal(
        @Query("c") c:String):Response<FoodResponse>
    @GET("categories.php")
    suspend fun getCategoryOfMeal():Response<FoodCategory>
    @GET("lookup.php?")
    suspend fun getById(
        @Query("i") i:String):Response<FoodResponse>
    @GET("search.php?")
    suspend fun searMeals(
        @Query("s") s:String):Response<FoodResponse>






}