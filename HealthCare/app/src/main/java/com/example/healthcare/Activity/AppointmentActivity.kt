package com.example.healthcare.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthcare.databinding.ActivityAppointmentBinding

class AppointmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppointmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}