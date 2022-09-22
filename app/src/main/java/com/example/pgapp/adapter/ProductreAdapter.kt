package com.example.pgapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pgapp.databinding.ProductContainerBinding
import com.example.pgapp.model.Product

class ProductreAdapter(private val items: ArrayList<Product>)
    : RecyclerView.Adapter<ProductreAdapter.ViewHolder> () {
        inner class ViewHolder(val binding: ProductContainerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(items[position]) {
                binding.tvCatalogName.text = this.name
                binding.ivCatalogImage.setImageResource(this.image)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}