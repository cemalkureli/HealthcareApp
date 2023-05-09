package com.example.healthcare.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthcare.databinding.ActivityBuyMedicineBinding

class BuyMedicineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyMedicineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyMedicineBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}