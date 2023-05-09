package com.example.healthcare.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthcare.databinding.ActivityCartBuyMedicineBinding

class CartBuyMedicineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBuyMedicineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBuyMedicineBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}