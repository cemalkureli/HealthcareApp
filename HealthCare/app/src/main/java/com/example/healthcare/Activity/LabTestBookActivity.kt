package com.example.healthcare.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthcare.databinding.ActivityLabTestBookBinding

class LabTestBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLabTestBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLabTestBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}