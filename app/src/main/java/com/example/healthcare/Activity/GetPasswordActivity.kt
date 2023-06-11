package com.example.healthcare.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.healthcare.databinding.ActivityGetPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class GetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetPasswordBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()


        binding.sendPassword.setOnClickListener {
            val userEmail = binding.getPasswordEmail.text.toString()
            sendPasswordResetEmail(userEmail)
        }

        binding.goToLogin2.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun sendPasswordResetEmail(email: String) {
        val auth = FirebaseAuth.getInstance()
        val emailAddress = email.trim()

        if (emailAddress.isNotEmpty()) {
            auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Password reset email has been sent.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "No account found for this email.", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            binding.getPasswordEmail.error = "Email address cannot be empty."
            Toast.makeText(this, "Email address cannot be empty.", Toast.LENGTH_SHORT).show()
        }
    }
}