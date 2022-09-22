package com.example.pgapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pgapp.R
import com.example.pgapp.adapter.ProductreAdapter
import com.example.pgapp.databinding.CatalogContainerBinding
import com.example.pgapp.model.Product

class CatalogActivity : AppCompatActivity() {
    private lateinit var binding: CatalogContainerBinding
    private lateinit var productList: ArrayList<Product>
    private lateinit var productAdapter: ProductreAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CatalogContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fillProducts()
    }
    @SuppressLint("Recycle")
    private fun fillProducts() {
        productList = ArrayList()
        val arrayProductNames = resources.getStringArray(R.array.productname)
        val arrayImageNames = resources.obtainTypedArray(R.array.imagename)
        for(i in arrayProductNames.indices) {
            val products = Product()
            products.name = arrayProductNames[i]
            products.image = arrayImageNames.getResourceId(i, -1)
            productList.add(products)
        }
        productAdapter = ProductreAdapter(productList)
        binding.rvContenido.adapter = productAdapter
        binding.rvContenido.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}