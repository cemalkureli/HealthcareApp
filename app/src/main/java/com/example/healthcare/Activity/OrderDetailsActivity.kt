package com.example.healthcare.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.SimpleAdapter
import com.example.healthcare.Adapter.LineItemAdapter
import com.example.healthcare.Database.Database
import com.example.healthcare.R
import com.example.healthcare.databinding.ActivityOrderDetailsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList
import java.util.HashMap

class OrderDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderDetailsBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var lineAdapter: LineItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val firebaseUser = auth.currentUser
        if (firebaseUser != null) {
            db.collection("users").document(firebaseUser.uid).get()
                .addOnSuccessListener { document ->
                    val username = document.getString("username").toString()
                    val database = Database(applicationContext)
                    val dbData = database.getOrderData(username)

                    val orderDetails = Array(dbData.size) { arrayOfNulls<String>(7) }

                    for (i in 0 until orderDetails.size) {
                        orderDetails[i] = arrayOfNulls(7)
                        val arrData = dbData[i]
                        val strData = arrData.split("\\₺".toRegex()).toTypedArray()
                        orderDetails[i][0] = username // Username
                        orderDetails[i][1] = strData[1] // FULLName
                        orderDetails[i][2] = strData[2] // Address
                        orderDetails[i][3] = "${strData[7]}₺" // Amount
                        orderDetails[i][4] = if (strData[8] == "Medicine") {
                            "Delivery Date: ${strData[5]}" // Delivery Date
                        } else {
                            "Delivery Date/Time: ${strData[5]} ${strData[6]}" // Delivery Date/Time
                        }
                        orderDetails[i][5] = strData[8] // Type
                        orderDetails[i][6] = strData[9] // Product Name / Doctor Name
                    }

                    val list: MutableList<HashMap<String, String>> = mutableListOf()
                    for (i in orderDetails.indices) {
                        val item: HashMap<String, String> = HashMap()
                        item["line1"] = orderDetails[i][1] ?: ""
                        item["line2"] = orderDetails[i][2] ?: ""
                        item["line3"] = orderDetails[i][3] ?: ""
                        item["line4"] = orderDetails[i][4] ?: ""
                        item["line5"] = orderDetails[i][5] ?: ""
                        list.add(item)
                    }

                    lineAdapter = LineItemAdapter(this, list, orderDetails.toMutableList())
                    binding.gridviewOrders.adapter = lineAdapter
                }
        }
    }

    fun onBackButtonClick8(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}
