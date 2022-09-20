package com.example.pgapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pgapp.databinding.ProductContainerBinding
import com.example.pgapp.model.product

class productreAdapter(val context: Context, private val items: ArrayList<product>)
    : RecyclerView.Adapter<productreAdapter.ViewHolder> () {
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