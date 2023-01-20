package com.demir.foodapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.demir.foodapp.R
import com.demir.foodapp.databinding.FragmentCatagoriesBinding
import com.demir.foodapp.databinding.FragmentFoodBottomSheetBinding
import com.demir.foodapp.ui.viewmodel.FoodViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

private const val MEAL_ID= "param1"
class FoodBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFoodBottomSheetBinding
    private lateinit var viewModel: FoodViewModel
    private var mealId:String?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentFoodBottomSheetBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(FoodViewModel::class.java)
        arguments?.let {
           mealId= it.getString(MEAL_ID)
        }

        mealId?.let {
            viewModel.getById(it)
        }

        observeLiveMealData()
        bottomSheetClickListener()

    }

    private fun bottomSheetClickListener() {
        binding.bottomSheetScreen.setOnClickListener {
            val intent=Intent(context,MealActivity::class.java)
            intent.putExtra(HomeFragment.MEAL_ID,mealId)
            startActivity(intent)


        }
    }

    private fun observeLiveMealData() {
        viewModel.bottomSheetLiveData.observe(viewLifecycleOwner,Observer {
            it?.let {
                val detail=it.meals.get(0)
                Glide.with(this).load(detail.strMealThumb).into(binding.imgBottomSheetImage)
                binding.tvMealCategory.text=detail.strCategory
            }

        })
    }
    companion object{
        @JvmStatic
        fun newInsanse(param1:String)=
            FoodBottomSheetFragment().apply {
                arguments=Bundle().apply {
                    putString(MEAL_ID,param1)
                }
            }
    }

}