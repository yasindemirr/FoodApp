package com.demir.foodapp.ui.adepter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demir.foodapp.databinding.CategotyItemBinding
import com.demir.foodapp.databinding.PopularFoodItemBinding
import com.demir.foodapp.ui.model.Category
import com.demir.foodapp.ui.model.Meal

class CategotyAdepter(val categoryList:ArrayList<Category>):RecyclerView.Adapter<CategotyAdepter.CategoryHoler>() {
    class CategoryHoler(val binding:CategotyItemBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHoler {
        val view=CategotyItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryHoler(view)
    }

    override fun onBindViewHolder(holder: CategoryHoler, position: Int) {
        holder.itemView.apply {
            Glide.with(this).load(categoryList.get(position).strCategoryThumb).into(holder.binding.categoryItemImage)
        }
        holder.binding.categoryItemText.text=categoryList.get(position).strCategory
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(categoryList[position])
            }
        }

    }
    private var onItemClickListener: ((Category) -> Unit)? = null
    fun setOnItemClickListener(listener: (Category) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
    fun UpdateCategotyMeals(newPopularList:List<Category>){
        categoryList.clear()
        categoryList.addAll(newPopularList)
        notifyDataSetChanged()
    }
}