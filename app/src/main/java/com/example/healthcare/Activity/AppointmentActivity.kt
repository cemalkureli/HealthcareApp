package com.example.healthcare.Activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.healthcare.Database.Database
import com.example.healthcare.databinding.ActivityAppointmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class AppointmentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppointmentBinding
    private lateinit var timePicker : TimePickerDialog
    private lateinit var dataPicker : DatePickerDialog
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val currentDate = Date()
        val formattedDate = dateFormat.format(currentDate)
        binding.buttonAppDate.text = formattedDate

        binding.fullName.keyListener = null
        binding.addressDetail.keyListener = null
        binding.contactNumber.keyListener = null
        binding.fees.keyListener = null

        val title = intent.getStringExtra("text1")
        val fullName = intent.getStringExtra("text2").toString()
        val address = intent.getStringExtra("text3").toString()
        val contact = intent.getStringExtra("text4").toString()
        val fees = intent.getStringExtra("text5").toString()
        val feesFloat = fees.replace("₺", "").toFloat()

        val packageNameList = intent.getStringArrayListExtra("packageNameList")

        binding.titleCategory.text = title
        binding.fullName.setText(fullName)
        binding.addressDetail.setText(address)
        binding.contactNumber.setText(contact)
        binding.fees.setText(fees)


        initDatePicker()

        binding.buttonAppDate.setOnClickListener {
            dataPicker.show()
        }

        initTimePicker()

        binding.buttonAppTime.setOnClickListener {
            timePicker.show()
        }

        binding.bookAppointment.setOnClickListener {
            val firebaseUser = auth.currentUser
            if (firebaseUser != null) {
                db.collection("users").document(firebaseUser.uid).get()
                    .addOnSuccessListener { document ->
                        val username = document.getString("username").toString()
                        val database = Database(applicationContext)
                        val date = (binding.buttonAppDate.text).toString()
                        val time = (binding.buttonAppTime.text).toString()
                        if (database.checkAppointmentExists(username,title+" => "+fullName,address,contact,date,time)==1) {
                            Toast.makeText(applicationContext, "Appointment Already Booked", Toast.LENGTH_SHORT).show()
                        } else {
                            if (packageNameList != null) {
                                database.addOrder(username, title+" => "+fullName, address, contact, 0, date,time, feesFloat, "Appointment", packageNameList)
                            }
                            Toast.makeText(applicationContext, "Booking Process Completed Successfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@AppointmentActivity, HomeActivity::class.java))
                        }
                    }
            }
        }
    }

    fun onBackButtonClick4(view: View) {
        val intent = Intent(this, FindDoctorActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun initDatePicker() {
        // DatePicker'da bir tarih seçildiğinde tetiklenecek listener'ı oluşturuyoruz
        val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
            val month = monthOfYear + 1
            binding.buttonAppDate.text = "$dayOfMonth/$month/$year"
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

    private fun initTimePicker() {
        // TimePicker'da bir saat seçildiğinde tetiklenecek listener'ı oluşturuyoruz
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
            binding.buttonAppTime.text = "$hourOfDay:$minute"
        }
        val cal = Calendar.getInstance()
        val hours = cal.get(Calendar.HOUR_OF_DAY)
        val minutes = cal.get(Calendar.MINUTE)
        val style = AlertDialog.THEME_HOLO_DARK
        timePicker = TimePickerDialog(this, style, timeSetListener, hours, minutes, true)
    }

}
