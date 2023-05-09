package com.example.healthcare.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.healthcare.Database.Database
import com.example.healthcare.R
import com.example.healthcare.databinding.ActivityBmiactivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class BMIActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBmiactivityBinding
    private var gender = Gender.MALE
    private var bmi: Double = 0.0
    private lateinit var genderString : String
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        setNumberPickers()

        binding.weightPicker.setOnValueChangedListener { _, _, _ ->
            calculateBMI()
        }

        binding.heightPicker.setOnValueChangedListener { _, _, _ ->
            calculateBMI()
        }

        binding.maleButton.setOnClickListener {
            gender = Gender.MALE
            genderString = "Erkek"
            binding.maleButton.isSelected = true
            binding.femaleButton.isSelected = false
            calculateBMI()
        }

        binding.femaleButton.setOnClickListener {
            gender = Gender.FEMALE
            genderString = "Kadın"
            binding.maleButton.isSelected = false
            binding.femaleButton.isSelected = true
            calculateBMI()
        }
    }

    private fun setNumberPickers() {
        binding.weightPicker.apply {
            minValue = 30
            maxValue = 150
        }

        binding.heightPicker.apply {
            minValue = 100
            maxValue = 250
        }
    }

    private fun calculateBMI() {
        val height = binding.heightPicker.value / 100.0
        val weight = binding.weightPicker.value.toDouble()
        bmi = weight / (height * height)

        val result = String.format("BMI Değeri: %.2f",bmi)
        binding.valueBMI.text = result

        binding.saveBMIButton.setOnClickListener {
            val firebaseUser = auth.currentUser
            val email = firebaseUser?.email.toString()
            if (firebaseUser != null) {
                db.collection("users").document(firebaseUser.uid).get().addOnSuccessListener { document ->
                    val username = document.getString("username")
                    if (username != null && bmi!= 0.0) {
                        if (::genderString.isInitialized) { // :: operatorü, bir fonksiyonun ya da bir değişkenin referansını almak için kullanılan bir operatördür
                            val database = Database(applicationContext)
                            database.saveBMI(username, email, genderString, bmi)
                            Toast.makeText(this, "BMI Değeri Kayıt Edildi.", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "Lütfen bir cinsiyet seçeneği belirleyin.", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        if (gender == Gender.MALE) {
            bmiCalMale(bmi)
        } else {
            bmiCalFemale(bmi)
        }
    }

    private fun bmiCalMale(bmi: Double) {
        binding.textBMI.text = when {
            bmi < 20 -> getString(R.string.bmi_result, getString(R.string.bmi_underweight))
            bmi < 25 -> getString(R.string.bmi_result, getString(R.string.bmi_normal))
            bmi < 30 -> getString(R.string.bmi_result, getString(R.string.bmi_overweight))
            else -> getString(R.string.bmi_result, getString(R.string.bmi_obese))
        }
    }

    private fun bmiCalFemale(bmi: Double) {
        binding.textBMI.text = when {
            bmi < 19 -> getString(R.string.bmi_result, getString(R.string.bmi_underweight))
            bmi < 24 -> getString(R.string.bmi_result, getString(R.string.bmi_normal))
            bmi < 29 -> getString(R.string.bmi_result, getString(R.string.bmi_overweight))
            else -> getString(R.string.bmi_result, getString(R.string.bmi_obese))
        }
    }
    //Sabitler Kümesi Sınıfı
    enum class Gender {
        MALE,
        FEMALE
    }
    fun onBackButtonClick(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}

