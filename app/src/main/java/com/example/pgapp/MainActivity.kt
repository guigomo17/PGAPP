package com.example.pgapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.pgapp.adapter.ImagereAdapter
import com.example.pgapp.databinding.ActivityMainBinding
import com.example.pgapp.databinding.CatalogContainerBinding
import com.example.pgapp.ui.CatalogActivity
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapterImage: ImagereAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: CatalogContainerBinding

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
            startActivity(Intent(this, CatalogActivity::class.java))
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

        adapterImage =ImagereAdapter(imageList, viewPager2)

        viewPager2.adapter = adapterImage
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }
}