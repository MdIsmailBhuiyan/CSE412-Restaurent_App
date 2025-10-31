package com.ewubd.cravecart.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ewubd.cravecart.DetailsActivity
import com.ewubd.cravecart.databinding.PopularItemsBinding

class popularAdapter (private val items:List<String>, private val price: List<String>, private val image: List<Int>, private val requiredContext: Context): RecyclerView.Adapter<popularAdapter.populerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): populerViewHolder {
        return populerViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun onBindViewHolder(holder: populerViewHolder, position: Int) {
        val item = items[position]
        val price = price[position]
        val images = image[position]
        holder.bind(item, price, images)

        holder.itemView.setOnClickListener{
            val intent = Intent(requiredContext, DetailsActivity::class.java)
            intent.putExtra("MenuItemName", item)
            intent.putExtra("MenuItemImage", images)
            requiredContext.startActivities(arrayOf(intent))
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
    class populerViewHolder (private val binding: PopularItemsBinding) : RecyclerView.ViewHolder(binding.root){
        val imagesView = binding.foodItem
        fun bind(item: String, price: String, images: Int) {
            binding.FoodNamePopular.text = item
            binding.pricePopular.text = price
            imagesView.setImageResource(images)
        }

    }
}