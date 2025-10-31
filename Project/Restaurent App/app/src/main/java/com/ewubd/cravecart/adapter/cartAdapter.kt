package com.ewubd.cravecart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ewubd.cravecart.databinding.CartItemBinding

class cartAdapter(private val CartItems:MutableList<String>, private val CartItemPrice: MutableList<String>, private val CartImage: MutableList<Int> ) : RecyclerView.Adapter<cartAdapter.cartViewHolder>(){

    private val itemquantity = IntArray(CartItems.size){1}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return cartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: cartViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int = CartItems.size

    inner class cartViewHolder(private val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemquantity[position]
                cartFoodName.text = CartItems[position]
                cartItemPrice.text = CartItemPrice[position]
                cartImage.setImageResource(CartImage[position])
                cartItemquantity.text =  quantity.toString()

                minusButton.setOnClickListener{
                    decreaseQuantity(position)
                }
                plusButton.setOnClickListener{
                    increaseQuantity(position)
                }
                deleteButton.setOnClickListener{
                    val itemPosition =adapterPosition
                    if(itemPosition != RecyclerView.NO_POSITION){
                        deleteItem(itemPosition)
                    }
                }
            }

        }
        private fun decreaseQuantity(position: Int){
            if(itemquantity[position]>1){
                itemquantity[position]--
                binding.cartItemquantity.text = itemquantity[position].toString()
            }
        }
        private fun increaseQuantity(position: Int){
            if(itemquantity[position]<10){
                itemquantity[position]++
                binding.cartItemquantity.text = itemquantity[position].toString()
            }
        }
        private fun deleteItem(position: Int){
            CartItems.removeAt(position)
            CartImage.removeAt(position)
            CartItemPrice.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, CartItems.size)
        }

    }
}