package com.demir.foodapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.demir.foodapp.R
import com.demir.foodapp.databinding.FragmentCatagoriesBinding
import com.demir.foodapp.ui.adepter.CategotyAdepter
import com.demir.foodapp.ui.viewmodel.FoodViewModel


class CatagoriesFragment : Fragment() {
    private lateinit var binding: FragmentCatagoriesBinding
    private lateinit var viewModel:FoodViewModel
    private val catAdepter= CategotyAdepter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentCatagoriesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(FoodViewModel::class.java)
        binding.ctgryRecyclerView.adapter=catAdepter
        binding.ctgryRecyclerView.layoutManager=GridLayoutManager(context,3,LinearLayoutManager.VERTICAL,false)
        viewModel.getCategotyOfMeal()
        observeCategoryData()
    }

    private fun observeCategoryData() {
        viewModel.categoryMeal.observe(viewLifecycleOwner,Observer{
            catAdepter.UpdateCategotyMeals(it.categories)
        })
    }


}