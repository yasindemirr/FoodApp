package com.demir.foodapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.demir.foodapp.R
import com.demir.foodapp.databinding.FragmentSearchNewsBinding
import com.demir.foodapp.ui.adepter.SavedMealAdepter
import com.demir.foodapp.ui.viewmodel.FoodViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment() {
    private lateinit var binding:FragmentSearchNewsBinding
    private lateinit var viewModel:FoodViewModel
    private val searchAdepter=SavedMealAdepter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSearchNewsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        binding.searchRec.adapter = searchAdepter
        binding.searchRec.layoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        observeSearchLiveData()
        var job: Job? = null
        binding.searchBox.addTextChangedListener { editable ->
            job?.cancel()
            job = lifecycleScope.launch {
                delay(500L)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchMeal(editable.toString())

                    }else{
                        binding.searchRec.visibility=View.GONE
                    }
                }
            }
        }
    }

    private fun observeSearchLiveData() {
        viewModel.searchMeal.observe(viewLifecycleOwner,Observer{
            searchAdepter.differ.submitList(it.meals)
            binding.searchRec.visibility=View.VISIBLE
            binding.errorMassage.visibility=View.GONE
        })
        viewModel.situationMassage.observe(viewLifecycleOwner,Observer{
            binding.searchRec.visibility=View.VISIBLE
            binding.searchRec.visibility=View.GONE
        })
    }
}