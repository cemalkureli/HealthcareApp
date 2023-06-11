package com.example.healthcare.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.healthcare.Database.Database
import com.example.healthcare.databinding.ActivityLabTestDetailsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LabTestDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLabTestDetailsBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLabTestDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.packageDetailsEditText.keyListener = null


        val titlePackage = intent.getStringExtra("text1")
        val detailsPackage = intent.getStringExtra("text2")
        val totalCost = intent.getStringExtra("text3")+"₺"

        binding.titlePackage.setText(titlePackage)
        binding.packageDetailsEditText.setText(detailsPackage)
        binding.totalCost.setText(totalCost)


        binding.addToCard.setOnClickListener {
            val firebaseUser = auth.currentUser
            val product = binding.titlePackage.text.toString()
            val price = intent.getStringExtra("text3")?.toFloatOrNull() ?: 0f

            if (firebaseUser != null) {
                db.collection("users").document(firebaseUser.uid).get().addOnSuccessListener { document ->
                    val username = document.getString("username")
                    val db = Database(applicationContext)
                    if (username != null) {
                        if (db.checkCart(username, product) == 1) {
                            Toast.makeText(
                                applicationContext,
                                "Product Already Added to Cart",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            db.addCart(username, product, price, "Laboratory")
                            Toast.makeText(applicationContext, "Product Added to Cart", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LabTestDetailsActivity, LabTestActivity::class.java))
                        }
                    }
                }
            }
        }
    }

    fun onBackButtonClick6(view: View) {
        val intent = Intent(this, LabTestActivity::class.java)
        startActivity(intent)
        finish()
    }
}