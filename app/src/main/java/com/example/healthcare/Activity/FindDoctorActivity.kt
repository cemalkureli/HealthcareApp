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

        binding.pediatrician.setOnClickListener {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("title", "Pediatricians")
            startActivity(intent)
        }
        binding.psychiatrist.setOnClickListener {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("title", "Psychiatrists")
            startActivity(intent)
        }
        binding.nutritionist.setOnClickListener {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("title", "Nutritionists")
            startActivity(intent)
        }
        binding.dermatologist.setOnClickListener {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("title", "Dermatologists")
            startActivity(intent)
        }
        binding.familydoctor.setOnClickListener {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("title", "Family Doctors")
            startActivity(intent)
        }
        binding.dentist.setOnClickListener {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("title", "Dentists")
            startActivity(intent)
        }
        binding.cardiologist.setOnClickListener {
            val intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("title", "Cardiologists")
            startActivity(intent)
        }
    }

    fun onBackButtonClick2(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }



}