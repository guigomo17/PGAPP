package com.example.pgapp

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class imageAdapter(private  val imageList: ArrayList<Int>, private val viewPager2: ViewPager2) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder> () {
    class ImageViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val imageView : ImageView = itemView.findViewById();
    }
}