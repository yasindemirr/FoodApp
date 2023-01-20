package com.demir.foodapp.ui.db

import android.content.Context
import androidx.room.*
import com.demir.foodapp.ui.model.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(MealTypeConverters::class)
abstract class FoodDataBase:RoomDatabase() {
    abstract fun getMealDao():FoodDao
    companion object{
        @Volatile private var instance: FoodDataBase?=null
        private val lock=Any()
        operator fun invoke(context: Context)= instance?: synchronized(lock){
            instance?: makeDataBase(context).also {
                instance=it
            }
        }
        private fun makeDataBase(context: Context)= Room.databaseBuilder(
            context.applicationContext,FoodDataBase::class.java,"fooddatabase").build()


    }
}