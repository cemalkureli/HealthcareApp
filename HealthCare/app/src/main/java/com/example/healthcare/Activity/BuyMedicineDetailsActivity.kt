package com.example.healthcare.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthcare.databinding.ActivityBuyMedicineDetailsBinding

class BuyMedicineDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyMedicineDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyMedicineDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}