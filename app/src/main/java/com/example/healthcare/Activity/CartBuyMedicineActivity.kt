package com.example.healthcare.Activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SimpleAdapter
import com.example.healthcare.Database.Database
import com.example.healthcare.R
import com.example.healthcare.databinding.ActivityCartBuyMedicineBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class CartBuyMedicineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBuyMedicineBinding
    private lateinit var dataPicker : DatePickerDialog
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var sAdapter: SimpleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBuyMedicineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val currentDate = Date()
        val formattedDate = dateFormat.format(currentDate)
        binding.buttonAppDate3.text = formattedDate

        initDatePicker()

        binding.buttonAppDate3.setOnClickListener {
            dataPicker.show()
        }

        val packageNameList: ArrayList<String> = ArrayList()

        val firebaseUser = auth.currentUser

        if (firebaseUser != null) {
            db.collection("users").document(firebaseUser.uid).get()
                .addOnSuccessListener { document ->
                    val username = document.getString("username").toString()
                    val database = Database(applicationContext)
                    var totalAmount = 0f
                    val dbData = database.getCartData(username, "Medicine")

                    val packages = Array(dbData.size) { arrayOfNulls<String>(5) }

                    for (i in 0 until dbData.size) {
                        val arrData = dbData[i]
                        val strData = arrData.split("\\₺".toRegex()).toTypedArray()
                        packages[i][0] = strData[0]
                        packages[i][4] = "Cost: ${strData[1]}₺/-"
                        totalAmount += strData[1].toFloat()
                    }

                    binding.totalCost3.text = "Total Cost: $totalAmount"+"₺"

                    val list: ArrayList<HashMap<String, String>> = ArrayList()
                    for (i in packages.indices) {
                        val item: HashMap<String, String> = HashMap()
                        item["line1"] = packages[i][0] ?: ""
                        item["line2"] = packages[i][1] ?: ""
                        item["line3"] = packages[i][2] ?: ""
                        item["line4"] = packages[i][3] ?: ""
                        item["line5"] = packages[i][4] ?: ""
                        list.add(item)

                        val packageName = packages[i][0] ?: ""
                        packageNameList.add(packageName)
                    }

                    sAdapter = SimpleAdapter(
                        this,
                        list,
                        R.layout.multi_lines,
                        arrayOf("line1", "line2", "line3", "line4", "line5"),
                        intArrayOf(R.id.line1, R.id.line2, R.id.line3, R.id.line4, R.id.line5))

                    binding.gridviewMedicine.adapter = sAdapter

                }
        }

        binding.checkoutButton.setOnClickListener {
            val intent = Intent(this, BuyMedicineBookActivity::class.java)
            val price = binding.totalCost3.text.toString().replace("Total Cost: ","")
            intent.putExtra("price", price)
            intent.putExtra("date", binding.buttonAppDate3.text)
            intent.putStringArrayListExtra("packageNameList", packageNameList)
            startActivity(intent)
            finish()
        }

    }

    private fun initDatePicker() {
        // DatePicker'da bir tarih seçildiğinde tetiklenecek listener'ı oluşturuyoruz
        val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
            val month = monthOfYear + 1
            binding.buttonAppDate3.text = "$dayOfMonth/$month/$year"
        }
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val style = AlertDialog.THEME_HOLO_DARK
        dataPicker = DatePickerDialog(this, style, dateSetListener, year, month, day)

        // Minimum tarih seçimini bugünkü tarih + 1 gün olarak ayarlıyoruz
        dataPicker.datePicker.minDate = cal.timeInMillis + 86400000
    }

    fun onBackButtonClick11(view: View) {
        val intent = Intent(this, BuyMedicineActivity::class.java)
        startActivity(intent)
        finish()
    }
}