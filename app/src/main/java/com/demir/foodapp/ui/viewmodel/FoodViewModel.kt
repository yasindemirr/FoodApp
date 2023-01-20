package com.demir.foodapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demir.foodapp.ui.model.FoodCategory
import com.demir.foodapp.ui.model.FoodResponse
import com.demir.foodapp.ui.model.Meal
import com.demir.foodapp.ui.repository.FoodRepository
import kotlinx.coroutines.launch

class FoodViewModel(application: Application):AndroidViewModel(application) {
    val repository=FoodRepository(application)
    val randomMeal=MutableLiveData<FoodResponse>()
    val randomMealDetail=MutableLiveData<FoodResponse>()
    val popularMeals=MutableLiveData<FoodResponse>()
    val categoryMeal=MutableLiveData<FoodCategory>()
    val bottomSheetLiveData=MutableLiveData<FoodResponse>()
    val searchMeal=MutableLiveData<FoodResponse>()
    val situationMassage=MutableLiveData<Boolean>()


    fun getRandomMeal()=viewModelScope.launch {
        val response=repository.getRandomMeal()
        if (response.isSuccessful){
            response.body()?.let {
                randomMeal.value=it
            }
        }
    }
    fun searchMeal(s:String)=viewModelScope.launch {
        val response=repository.searchMeals(s)
        if (response.isSuccessful){
            response.body()?.let {
                searchMeal.value=it

            }
        }
    }

    fun getRandomMealetail(i:String)=viewModelScope.launch {
        val response=repository.getRandomMealDetail(i)
        if (response.isSuccessful){
            response.body()?.let {
                randomMealDetail.value=it
            }
        }
    }
    fun getById(i:String)=viewModelScope.launch {
        val response=repository.getById(i)
        if (response.isSuccessful){
            response.body()?.let {
                bottomSheetLiveData.value=it

            }
        }
    }
    fun getPopularMeal(c:String)=viewModelScope.launch {
        val response=repository.getPopularMeal(c)
        if (response.isSuccessful){
            response.body()?.let {
                popularMeals.value=it

            }
        }
    }
    fun getCategotyOfMeal()=viewModelScope.launch {
        val response=repository.getCategoryOfMeal()
        if (response.isSuccessful){
            response.body()?.let {
                categoryMeal.value=it

            }
        }
    }
    fun upsert(meal: Meal)=viewModelScope.launch {
        repository.upsert(meal)
    }
    fun deleteMeal(meal: Meal)=viewModelScope.launch {
        repository.deleteMeal(meal)
    }
    fun realAllMeal():LiveData<List<Meal>>{
        return repository.realAllMeal()
    }


}