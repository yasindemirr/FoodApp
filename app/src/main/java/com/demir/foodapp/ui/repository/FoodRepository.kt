package com.demir.foodapp.ui.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.demir.foodapp.ui.api.ApiService
import com.demir.foodapp.ui.api.FoodApi
import com.demir.foodapp.ui.db.FoodDataBase
import com.demir.foodapp.ui.model.FoodCategory
import com.demir.foodapp.ui.model.FoodResponse
import com.demir.foodapp.ui.model.Meal
import retrofit2.Response

class FoodRepository(application: Application) {
    val apiService=ApiService
    val db=FoodDataBase(application)
    suspend fun getRandomMeal():Response<FoodResponse>{
        return apiService.api.getRandomMeal()
    }
    suspend fun getRandomMealDetail(i:String):Response<FoodResponse>{
        return apiService.api.getRandomMealDetail(i)
    }
    suspend fun getById(i:String):Response<FoodResponse>{
        return apiService.api.getById(i)
    }
    suspend fun getPopularMeal(c:String):Response<FoodResponse> {
        return apiService.api.getPopularMeal(c)
    }
    suspend fun searchMeals(s:String):Response<FoodResponse>{
        return apiService.api.searMeals(s)
    }
    suspend fun getCategoryOfMeal():Response<FoodCategory>{
        return apiService.api.getCategoryOfMeal()
    }
    suspend fun upsert(meal: Meal){
        db.getMealDao().upsert(meal)
    }
    suspend fun deleteMeal(meal:Meal){
        db.getMealDao().deleteMeal(meal)
    }
    fun realAllMeal()= db.getMealDao().realAllMealData()

}