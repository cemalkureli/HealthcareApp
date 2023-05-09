package com.example.healthcare.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthcare.databinding.ActivityBuyMedicineBookBinding

class BuyMedicineBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyMedicineBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyMedicineBookBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}