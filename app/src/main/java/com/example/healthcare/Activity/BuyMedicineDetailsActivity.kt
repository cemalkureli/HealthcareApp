package com.example.healthcare.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.healthcare.Database.Database
import com.example.healthcare.databinding.ActivityBuyMedicineDetailsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class BuyMedicineDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyMedicineDetailsBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyMedicineDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.medicineDetailsEditText.keyListener = null

        val titleMedicine = intent.getStringExtra("text1")
        val detailsMedicine = intent.getStringExtra("text2")
        val totalCost = intent.getStringExtra("text3")+"₺"

        binding.titlePackage2.setText(titleMedicine)
        binding.medicineDetailsEditText.setText(detailsMedicine)
        binding.totalCost.setText(totalCost)

        binding.addToCard.setOnClickListener {
            val firebaseUser = auth.currentUser
            val product = binding.titlePackage2.text.toString()
            val price = intent.getStringExtra("text3")?.toFloatOrNull() ?: 0f

            if (firebaseUser != null) {
                db.collection("users").document(firebaseUser.uid).get().addOnSuccessListener { document ->
                    val username = document.getString("username")
                    val db = Database(applicationContext)
                    if (username != null) {
                        if (db.checkCart(username, product) == 1) {
                            Toast.makeText(
                                applicationContext,
                                "Medicine Already Added to Cart",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            db.addCart(username, product, price, "Medicine")
                            Toast.makeText(applicationContext, "Medicine Added to Cart", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@BuyMedicineDetailsActivity, BuyMedicineActivity::class.java))
                        }
                    }
                }
            }
        }
    }
    fun onBackButtonClick10(view: View) {
        val intent = Intent(this, BuyMedicineActivity::class.java)
        startActivity(intent)
        finish()
    }
}