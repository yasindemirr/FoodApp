package com.demir.foodapp.ui.db
import androidx.lifecycle.LiveData
import androidx.room.*
import com.demir.foodapp.ui.model.Meal


@Dao
interface FoodDao {
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun upsert(meal: Meal):Long
    @Query("SELECT*FROM meal")
    fun realAllMealData():LiveData<List<Meal>>
    @Delete
    suspend fun deleteMeal(meal: Meal)
}