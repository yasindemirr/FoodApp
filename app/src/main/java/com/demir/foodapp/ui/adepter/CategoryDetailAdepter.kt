package com.demir.foodapp.ui.adepter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demir.foodapp.databinding.CategotyItemBinding
import com.demir.foodapp.databinding.DetailCategoryItemBinding
import com.demir.foodapp.ui.model.Category
import com.demir.foodapp.ui.model.Meal

class CategoryDetailAdepter(val categoryDetailList:ArrayList<Meal>):RecyclerView.Adapter<CategoryDetailAdepter.CategoryDetailHolder>() {
    class CategoryDetailHolder(val binding: DetailCategoryItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailHolder {
        val view=DetailCategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryDetailHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryDetailHolder, position: Int) {
        holder.itemView.apply {
            Glide.with(this).load(categoryDetailList.get(position).strMealThumb).into(holder.binding.categotyItemDetailImage)
        }
        holder.binding.categoryDetailText.text=categoryDetailList.get(position).strMeal
    }

    override fun getItemCount(): Int {
        return categoryDetailList.size
    }
    fun UpdateCategotyDetail(newCategoryDetailList:List<Meal>){
        categoryDetailList.clear()
        categoryDetailList.addAll(newCategoryDetailList)
        notifyDataSetChanged()
    }
}