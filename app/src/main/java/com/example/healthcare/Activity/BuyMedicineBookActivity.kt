package com.example.healthcare.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.healthcare.Database.Database
import com.example.healthcare.databinding.ActivityBuyMedicineBookBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class BuyMedicineBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyMedicineBookBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyMedicineBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val date = intent.getStringExtra("date").toString()
        val price = intent.getStringExtra("price").toString()
        val priceFloat = price.replace("₺", "").toFloat()

        val packageNameList = intent.getStringArrayListExtra("packageNameList")

        binding.paymentButton2.setOnClickListener {
            val fullname = binding.checkoutName2.text.toString().trim()
            val address = binding.checkoutAddress2.text.toString().trim()
            val pin = binding.checkoutPinCode2.text.toString()
            val contact = binding.checkoutContactNumber2.text.toString()

            if (fullname.isBlank() || fullname.length > 15) {
                binding.checkoutName2.error = "Name should not be empty and must be maximum 15 characters"
                Toast.makeText(applicationContext, "Name should not be empty and must be maximum 15 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (address.isBlank() || address.length > 30) {
                binding.checkoutAddress2.error = "Address should not be empty and must be maximum 30 characters"
                Toast.makeText(applicationContext, "Address should not be empty and must be maximum 30 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pin.isBlank() || pin.length != 5) {
                binding.checkoutPinCode2.error = "Pin Code should not be empty and must be a 5-digit number"
                Toast.makeText(applicationContext, "Pin Code should not be empty and must be a 5-digit number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (contact.isBlank() || contact.length < 5 || contact.length > 15) {
                binding.checkoutContactNumber2.error = "Contact Number should not be empty and must be between 5 and 15 characters"
                Toast.makeText(applicationContext, "Contact Number should not be empty and must be between 5 and 15 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val firebaseUser = auth.currentUser
            if (fullname.isNotBlank() && address.isNotBlank() && pin.isNotBlank() && contact.isNotBlank() && pin.length == 5 && contact.length >= 5 && contact.length <= 15) {
                if (firebaseUser != null) {
                    db.collection("users").document(firebaseUser.uid).get()
                        .addOnSuccessListener { document ->
                            val username = document.getString("username").toString()
                            val database = Database(applicationContext)
                            if (packageNameList != null) {
                                database.addOrder(username, fullname, address, contact, pin.toInt(), date,"", priceFloat, "Medicine", packageNameList)
                            }
                            database.removeCart(username, "Medicine")
                            Toast.makeText(applicationContext, "Booking Process Completed Successfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@BuyMedicineBookActivity, HomeActivity::class.java))
                        }
                }
            }
        }
    }
}