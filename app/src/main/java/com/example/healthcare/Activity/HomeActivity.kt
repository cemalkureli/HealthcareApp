package com.example.healthcare.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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


    private lateinit var sharedPrefLog: SharedPreferences
    private var isFirstLogin: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        sharedPrefLog = getSharedPreferences("LogSharedPref", Context.MODE_PRIVATE)
        isFirstLogin = sharedPrefLog.getBoolean("isFirstLogin", true)

        if (isFirstLogin) {
            val firebaseUser = auth.currentUser
            if (firebaseUser != null) {
                db.collection("users").document(firebaseUser.uid).get()
                    .addOnSuccessListener { document ->
                        val username = document.getString("username")
                        if (username != null) {
                            Toast.makeText(this, "Welcome $username!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            isFirstLogin = false
            val editor = sharedPrefLog.edit()
            editor.putBoolean("isFirstLogin", isFirstLogin)
            editor.apply()
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

        findDoc = findViewById(R.id.findDoctor)
        findDoc.setOnClickListener {
            val intent = Intent(this, FindDoctorActivity::class.java)
            startActivity(intent)
            finish()
        }

        orderDetail = findViewById(R.id.orderDetails)
        orderDetail.setOnClickListener {
            val intent = Intent(this, OrderDetailsActivity::class.java)
            startActivity(intent)
            finish()
        }

        buyMedicine = findViewById(R.id.buyMedicine)
        buyMedicine.setOnClickListener {
            val intent = Intent(this, BuyMedicineActivity::class.java)
            startActivity(intent)
            finish()
        }

        labTest = findViewById(R.id.labTest)
        labTest.setOnClickListener {
            val intent = Intent(this, LabTestActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun logout() {
        val editor = sharedPrefLog.edit()
        editor.putBoolean("isFirstLogin", true)
        editor.apply()
        auth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
