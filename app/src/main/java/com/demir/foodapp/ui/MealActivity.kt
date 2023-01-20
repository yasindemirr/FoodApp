package com.demir.foodapp.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.demir.foodapp.R
import com.demir.foodapp.databinding.ActivityMealBinding
import com.demir.foodapp.ui.model.Meal
import com.demir.foodapp.ui.viewmodel.FoodViewModel
import com.google.android.material.snackbar.Snackbar

class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealBinding
    private lateinit var mealId:String
    private lateinit var meal: Meal
    private lateinit var viewModel:FoodViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this).get(FoodViewModel::class.java)

        getMealInformationFromIntent()
       // setInformations()
        viewModel.getRandomMealetail(mealId)
        observeMealDetailData()
        clickYoutbeButton()
        clickFavoriteButton()
    }

    private fun clickFavoriteButton() {
        binding.fab.setOnClickListener{
                viewModel.upsert(meal)
                Snackbar.make(it,"Meal save successfully", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun clickYoutbeButton() {
        binding.youtubeBtn.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(meal.strYoutube))
            startActivity(intent)

        }


    }

    private fun observeMealDetailData() {
        viewModel.randomMealDetail.observe(this, Observer {
            it.let {
                val detailMeal=it.meals[0]
                binding.categoryText.text="Catgory:${detailMeal.strCategory} "
                binding.instructionText.text=detailMeal.strInstructions
                binding.locationText.text="Location:${detailMeal.strArea} "
                Glide.with(applicationContext)
                    .load(detailMeal.strMealThumb).into(binding.imageMealDetail)
                binding.collapsingToolBar.title=detailMeal.strMeal
                binding.collapsingToolBar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
                meal=it.meals[0]
            }
        })
    }
    /*
    private fun setInformations() {
        Glide.with(applicationContext)
            .load(mealThumb).into(binding.imageMealDetail)
        binding.collapsingToolBar.title=mealName
        binding.collapsingToolBar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
    }

     */

    private fun getMealInformationFromIntent() {
        val intent=intent
        intent?.let {
        }
        mealId=intent.getStringExtra(HomeFragment.MEAL_ID)!!

    }




}