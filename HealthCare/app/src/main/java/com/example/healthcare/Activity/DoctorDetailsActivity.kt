package com.example.healthcare.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthcare.databinding.ActivityDoctorDetailsBinding

class DoctorDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}