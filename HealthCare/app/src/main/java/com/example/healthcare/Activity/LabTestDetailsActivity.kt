package com.example.healthcare.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthcare.databinding.ActivityLabTestDetailsBinding

class LabTestDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLabTestDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLabTestDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}