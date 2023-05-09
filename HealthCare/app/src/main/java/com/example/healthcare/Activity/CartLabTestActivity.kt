package com.example.healthcare.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthcare.databinding.ActivityCartLabTestBinding

class CartLabTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartLabTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartLabTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}