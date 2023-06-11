package com.example.healthcare.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import com.example.healthcare.Database.*
import com.example.healthcare.R
import com.example.healthcare.databinding.ActivityDoctorDetailsBinding

class DoctorDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctorDetailsBinding
    private lateinit var doctorDetails: Array<Array<String>>
    private lateinit var sAdapter: SimpleAdapter
    private lateinit var titleDoc: TextView
    private lateinit var title: String

    private val familyDoctors: ArrayList<Doktor> = ArrayList()
    private val pediatricianDoctors: ArrayList<Doktor> = ArrayList()
    private val nutritionistDoctors: ArrayList<Doktor> = ArrayList()
    private val psychiatristDoctors: ArrayList<Doktor> = ArrayList()
    private val dermatologistDoctors: ArrayList<Doktor> = ArrayList()
    private val dentistDoctors: ArrayList<Doktor> = ArrayList()
    private val cardiologistDoctors: ArrayList<Doktor> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        titleDoc = binding.titleDoc

        val intent = intent
        title = intent.getStringExtra("title").toString()
        titleDoc.text = title

        pediatricianDoctors.addAll(doctors1)
        nutritionistDoctors.addAll(doctors2)
        dermatologistDoctors.addAll(doctors3)
        psychiatristDoctors.addAll(doctors4)
        familyDoctors.addAll(doctors5)
        dentistDoctors.addAll(doctors6)
        cardiologistDoctors.addAll(doctors7)

        val packageNameList: java.util.ArrayList<String> = java.util.ArrayList()

        doctorDetails = when (title) {
            "Family Doctors" -> convertDoctorListToArray(familyDoctors)
            "Pediatricians" -> convertDoctorListToArray(pediatricianDoctors)
            "Nutritionists" -> convertDoctorListToArray(nutritionistDoctors)
            "Psychiatrists" -> convertDoctorListToArray(psychiatristDoctors)
            "Dermatologists" -> convertDoctorListToArray(dermatologistDoctors)
            "Dentists" -> convertDoctorListToArray(dentistDoctors)
            "Cardiologists" -> convertDoctorListToArray(cardiologistDoctors)
            else -> arrayOf()
        }

        val list: ArrayList<HashMap<String, String>> = ArrayList()
        for (i in doctorDetails.indices) {
            val item: HashMap<String, String> = HashMap()
            item["line1"] = "Doktor: ${doctorDetails[i][0]}"
            item["line2"] = "Hastane Adresi: ${doctorDetails[i][1]}"
            item["line3"] = doctorDetails[i][2]
            item["line4"] = "Telefon No: ${doctorDetails[i][3]}"
            item["line5"] = "Ücret: ${doctorDetails[i][4]}/-"
            list.add(item)

            val packageName = doctorDetails[i][0]
            packageNameList.add(packageName)
        }

        sAdapter = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line1, R.id.line2, R.id.line3, R.id.line4, R.id.line5)
        )
        val lst: ListView = findViewById(R.id.listviewDoc)
        lst.adapter = sAdapter


        lst.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this@DoctorDetailsActivity, AppointmentActivity::class.java)
            intent.putExtra("text1", title)
            intent.putExtra("text2", doctorDetails[i][0])
            intent.putExtra("text3", doctorDetails[i][1])
            intent.putExtra("text4", doctorDetails[i][3])
            intent.putExtra("text5", doctorDetails[i][4])
            intent.putStringArrayListExtra("packageNameList", packageNameList)
            startActivity(intent)
        }

    }

    fun onBackButtonClick3(view: View) {
        val intent = Intent(this, FindDoctorActivity::class.java)
        startActivity(intent)
        finish()
    }

    // Doktor listesini 2 boyutlu diziye dönüştüren fonksiyon
    private fun convertDoctorListToArray(doctors: ArrayList<Doktor>): Array<Array<String>> {
        val doctorArray = Array(doctors.size) { arrayOf("", "", "", "", "") }
        for (i in doctors.indices) {
            val doctor = doctors[i]
            doctorArray[i][0] = doctor.name + " " + doctor.surname
            doctorArray[i][1] = doctor.address
            doctorArray[i][2] = doctor.experience
            doctorArray[i][3] = doctor.phoneNumber
            doctorArray[i][4] = doctor.price
        }
        return doctorArray
    }
}
