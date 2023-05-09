package com.example.healthcare.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.healthcare.R
import com.example.healthcare.databinding.ActivityFindDoctorBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FindDoctorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFindDoctorBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onBackButtonClick2(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}