package com.example.healthcare.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthcare.databinding.ActivityLabTestBinding

class LabTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLabTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLabTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}