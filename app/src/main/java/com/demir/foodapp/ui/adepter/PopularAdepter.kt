package com.demir.foodapp.ui.adepter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demir.foodapp.databinding.PopularFoodItemBinding
import com.demir.foodapp.ui.model.Meal

class PopularAdepter(val popularList: ArrayList<Meal>):RecyclerView.Adapter<PopularAdepter.PopularHolder>() {
    class PopularHolder(val binding: PopularFoodItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularHolder {
        val view= PopularFoodItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularHolder(view)
    }

    override fun onBindViewHolder(holder: PopularHolder, position: Int) {
        holder.itemView.apply {
            Glide.with(this).load(popularList[position].strMealThumb).into(holder.binding.itemImage)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(popularList[position])
            }
        }
        holder.itemView.setOnLongClickListener {
            onLongItemClickListener?.let {
                it(popularList[position])
            }
            return@setOnLongClickListener true
        }

    }
    private var onItemClickListener: ((Meal) -> Unit)? = null
    fun setOnItemClickListener(listener: (Meal) -> Unit) {
        onItemClickListener = listener
    }
    private var onLongItemClickListener: ((Meal) -> Unit)? = null
    fun setOnItemLongClickListener(listener: (Meal) -> Unit) {
        onLongItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return popularList.size

    }
    fun UpdatePopularMeals(newPopularList:List<Meal>){
        popularList.clear()
        popularList.addAll(newPopularList)
        notifyDataSetChanged()
    }
}