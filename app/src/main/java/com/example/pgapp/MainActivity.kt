package com.example.pgapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.pgapp.adapter.imagereAdapter
import com.example.pgapp.adapter.productreAdapter
import com.example.pgapp.databinding.ActivityMainBinding
import com.example.pgapp.databinding.CatalogContainerBinding
import com.example.pgapp.model.product
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    lateinit var viewPager2: ViewPager2
    lateinit var handler: Handler
    lateinit var imageList: ArrayList<Int>
    lateinit var adapterImage: imagereAdapter
    lateinit var productList: ArrayList<product>
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: CatalogContainerBinding
    lateinit var adapterProduct: productreAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding2 = CatalogContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setUpTransformer()
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2000)
            }
        })
        binding.ivMenuCatalog.setOnClickListener {
            setContentView(binding2.root)
            layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding2.rvContenido.layoutManager = layoutManager
            fillProduct()
            adapterProduct = productreAdapter(this, productList)
          //  binding2.rvContenido.adapter = productreAdapter
        }
    }

    fun showActivity() {

    }

    fun fillProduct() {
        productList = ArrayList()
        val arrayProductNames = resources.getStringArray(R.array.productname)
        val arrayImageNames = resources.obtainTypedArray(R.array.imagename)
        for(i in arrayProductNames.indices) {
            val products = product()
            products.name = arrayProductNames[i]
            products.image = arrayImageNames.getResourceId(i, -1)
            productList.add(products)
        }
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer{ page, position ->
            val  r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewPager2.setPageTransformer(transformer)
    }

    private fun init (){
        viewPager2 = findViewById(R.id.vpMenuCarrusel)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.descuentoproductos)
        imageList.add(R.drawable.beneficiosagroalpha)
        imageList.add(R.drawable.beneficioskadabra)
        imageList.add(R.drawable.beneficiosviovan)

        adapterImage = imagereAdapter(imageList, viewPager2)

        viewPager2.adapter = adapterImage
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }
}