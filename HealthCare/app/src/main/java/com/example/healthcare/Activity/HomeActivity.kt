package com.example.healthcare.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.healthcare.R
import com.example.healthcare.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var exit : CardView
    private lateinit var findDoc : CardView
    private lateinit var orderDetail : CardView
    private lateinit var calculateBMI : CardView
    private lateinit var buyMedicine : CardView
    private lateinit var labTest : CardView
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            // Firestore'dan kullanıcının username'ini al
            db.collection("users").document(firebaseUser.uid).get().addOnSuccessListener { document ->
                val username = document.getString("username")
                if (username != null) {
                    Toast.makeText(this, "Hoşgeldiniz $username!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        exit = findViewById(R.id.exit)
        exit.setOnClickListener {
            logout()
        }

        calculateBMI = findViewById(R.id.CalculateBMI)
        calculateBMI.setOnClickListener {
            val intent = Intent(this, BMIActivity::class.java)
            startActivity(intent)
            finish()
        }


        // doktoru secip hastaneyi secip randevu alacak order detailde gorunecek
        findDoc = findViewById(R.id.findDoctor)
        findDoc.setOnClickListener {
            val intent = Intent(this, FindDoctorActivity::class.java)
            startActivity(intent)
            finish()
        }

/*
        orderDetail = findViewById(R.id.orderDetails)
        orderDetail.setOnClickListener {
            val intent = Intent(this, FindDoctorActivity::class.java)
            startActivity(intent)
            finish()
        }
        // medicini secip karta ekleyecek order detailden bakabilir tarihi ayarlayabilir bu ekranda
        buyMedicine = findViewById(R.id.buyMedicine)
        buyMedicine.setOnClickListener {
            val intent = Intent(this, FindDoctorActivity::class.java)
            startActivity(intent)
            finish()
        }
        // Package'ler olacak corona testi gibi bunları karta ekleyeceğim tarihi ayarlayabilir
        labTest = findViewById(R.id.labTest)
        labTest.setOnClickListener {
            val intent = Intent(this, FindDoctorActivity::class.java)
            startActivity(intent)
            finish()
        }
*/// Makale sayfası ve bir özellik daha ekleyeceğim

    }
    fun logout(){
            //SharedPreferences'in içini temizleme
            val sharedpreferences = getSharedPreferences("username_holder", Context.MODE_PRIVATE)
            val editor = sharedpreferences.edit()
            editor.clear()
            editor.apply()
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
