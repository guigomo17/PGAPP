package com.example.pgapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pgapp.databinding.CatalogContainerBinding

class CatalogActivity : AppCompatActivity() {
    lateinit var binding: CatalogContainerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CatalogContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}