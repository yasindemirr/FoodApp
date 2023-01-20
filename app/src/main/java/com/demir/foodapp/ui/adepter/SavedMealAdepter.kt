package com.demir.foodapp.ui.adepter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demir.foodapp.databinding.DetailCategoryItemBinding
import com.demir.foodapp.ui.model.Meal

class SavedMealAdepter():RecyclerView.Adapter<SavedMealAdepter.SaveMealHolder>() {
    class SaveMealHolder(val binding: DetailCategoryItemBinding) : RecyclerView.ViewHolder(binding.root)
    private val diffutil=object :DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,diffutil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveMealHolder {
        val view =
            DetailCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SaveMealHolder(view)
    }

    override fun onBindViewHolder(holder: SaveMealHolder, position: Int) {
        val meal=differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(meal.strMealThumb)
                .into(holder.binding.categotyItemDetailImage)
        }
        holder.binding.categoryDetailText.text = meal.strMeal
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}