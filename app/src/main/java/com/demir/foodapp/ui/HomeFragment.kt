package com.demir.foodapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.demir.foodapp.R
import com.demir.foodapp.databinding.FragmentHomeBinding
import com.demir.foodapp.ui.adepter.CategotyAdepter
import com.demir.foodapp.ui.adepter.PopularAdepter
import com.demir.foodapp.ui.model.Meal
import com.demir.foodapp.ui.viewmodel.FoodViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: FoodViewModel
    private lateinit var meal:Meal
    private val popularAdepter=PopularAdepter(arrayListOf())
    private val categoryAdepter=CategotyAdepter(arrayListOf())
    companion object{
        val MEAL_ID="id"
        val MEAL_NAME= "name"
        val MEAL_THUMB="thumb"
        val MEAL_CATEGORY="category"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(FoodViewModel::class.java)
        binding.recylerViewPopular.adapter=popularAdepter
        binding.recylerViewPopular.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,
        false)
        binding.recylerViewCatagory.adapter=categoryAdepter
        binding.recylerViewCatagory.layoutManager=GridLayoutManager(context,3,LinearLayoutManager.VERTICAL,false)
        viewModel.getRandomMeal()
        viewModel.getPopularMeal("Beef")
        viewModel.getCategotyOfMeal()
        observePopularMeals()
        observeRandomMealData()
        observeCategoryMeal()
        randomMealClickListener()
        popularMealClickLister()
        observeCategoryFood()
        categoryClickListener()
        popularLongClick()
        searchItemClickListener()
    }

    private fun searchItemClickListener() {
        binding.searchItem.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_searchNewsFragment)
        }
    }

    private fun popularLongClick() {
        popularAdepter.setOnItemLongClickListener {meal->
          val foodBottomSheetFragment=FoodBottomSheetFragment.newInsanse(meal.idMeal)

            foodBottomSheetFragment.show(childFragmentManager,"Meal Info")
        }
    }

    private fun categoryClickListener() {
        categoryAdepter.setOnItemClickListener {
            val intent=Intent(context,CategotyFoodActivity::class.java)
            intent.putExtra(MEAL_CATEGORY,it.strCategory)
            startActivity(intent)


        }
    }

    private fun observeCategoryFood() {
        viewModel.categoryMeal.observe(viewLifecycleOwner,Observer{
            it?.let{
                categoryAdepter.UpdateCategotyMeals(it.categories)
            }
        })
    }

    private fun observeCategoryMeal() {

    }

    private fun popularMealClickLister() {
        popularAdepter.setOnItemClickListener {
            val intent=Intent(context,MealActivity::class.java)
            intent.putExtra(MEAL_ID,it.idMeal)
            startActivity(intent)
        }
    }

    private fun observePopularMeals() {
        viewModel.popularMeals.observe(viewLifecycleOwner, Observer {
            it?.let {
                popularAdepter.UpdatePopularMeals(it.meals)

            }
        })
    }

    private fun randomMealClickListener() {
        binding.imageRandomMeal.setOnClickListener {
            val intent= Intent(context,MealActivity::class.java)
            intent.putExtra(MEAL_ID,meal.idMeal)
            startActivity(intent)
        }
    }

    private fun observeRandomMealData() {
        viewModel.randomMeal.observe(viewLifecycleOwner, Observer {
            it?.let {
                    Glide.with(this).load(it.meals.get(0).strMealThumb).
                    into(binding.imageRandomMeal)
                meal=it.meals[0]
            }
        })
    }



}