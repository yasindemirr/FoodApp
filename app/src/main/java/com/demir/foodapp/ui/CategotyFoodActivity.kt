package com.demir.foodapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.demir.foodapp.R
import com.demir.foodapp.databinding.ActivityCategotyFoodBinding
import com.demir.foodapp.ui.adepter.CategoryDetailAdepter
import com.demir.foodapp.ui.viewmodel.FoodViewModel

class CategotyFoodActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCategotyFoodBinding
    private lateinit var viewModel:FoodViewModel
    private lateinit var getCategory:String
    private val categoryDetailAdepter=CategoryDetailAdepter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCategotyFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this).get(FoodViewModel::class.java)
        binding.rclerCategory.adapter=categoryDetailAdepter
        binding.rclerCategory.layoutManager=GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false)
        getInformationFromHomeFragment()
        viewModel.getPopularMeal(getCategory)
        observeWhichCategoryOfMeal()
    }

    private fun observeWhichCategoryOfMeal() {
        viewModel.popularMeals.observe(this, Observer {
            it?.let {
                categoryDetailAdepter.UpdateCategotyDetail(it.meals)
                binding.foodCount.text="Count: ${it.meals.size}"


            }

        })
    }

    private fun getInformationFromHomeFragment() {
        val intent=intent
        getCategory=intent.getStringExtra(HomeFragment.MEAL_CATEGORY)!!

    }

}