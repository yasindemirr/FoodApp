package com.demir.foodapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demir.foodapp.R
import com.demir.foodapp.databinding.FragmentFavoritesBinding
import com.demir.foodapp.ui.adepter.SavedMealAdepter
import com.demir.foodapp.ui.model.Meal
import com.demir.foodapp.ui.viewmodel.FoodViewModel
import com.google.android.material.snackbar.Snackbar

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel:FoodViewModel
    private val saveAdepter=SavedMealAdepter()
    private lateinit var mealList: List<Meal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentFavoritesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(FoodViewModel::class.java)
        binding.SaveRecyclerView.adapter=saveAdepter
        binding.SaveRecyclerView.layoutManager=GridLayoutManager(context,2,LinearLayoutManager.VERTICAL,false)
        observeRealAllMeal()
        remoteSavedMeals(view)

    }

    private fun remoteSavedMeals(view: View) {
        val itemTouchHelperCallBack=object :ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position= viewHolder.adapterPosition
               val meal =saveAdepter.differ.currentList.get(position)
                viewModel.deleteMeal(meal)
                Snackbar.make(view,"Deleted article successfully", Snackbar.LENGTH_SHORT).apply {
                    setAction("undo"){
                        viewModel.upsert(meal)
                    }
                    show()
                }
            }

        }
        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(binding.SaveRecyclerView)
        }
    }

    private fun observeRealAllMeal() {
        viewModel.realAllMeal().observe(viewLifecycleOwner,Observer{
            it?.let {
                saveAdepter.differ.submitList(it)
            }

        })
    }
}