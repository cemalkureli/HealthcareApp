package com.example.healthcare.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.SimpleAdapter
import com.example.healthcare.Database.PackagesAndDetails2
import com.example.healthcare.R
import com.example.healthcare.databinding.ActivityBuyMedicineBinding

class BuyMedicineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyMedicineBinding
    private lateinit var sAdapter: SimpleAdapter
    val medicinesList: ArrayList<Array<String>> = ArrayList()
    val medicineDetailsList: ArrayList<String> = ArrayList()

    val Medicines = PackagesAndDetails2()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyMedicineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (i in Medicines.medicines.indices) {
            medicinesList.add(Medicines.medicines[i])
            medicineDetailsList.add(Medicines.medicineDetails[i])
        }
        val list: ArrayList<HashMap<String, String>> = ArrayList()
        for (i in medicinesList.indices) {
            val item: HashMap<String, String> = HashMap()
            item["line1"] = medicinesList[i][0]
            item["line2"] = medicinesList[i][1]
            item["line3"] = medicinesList[i][2]
            item["line4"] = medicinesList[i][3]
            item["line5"] = "Total Cost: ${medicinesList[i][4]}₺/-"
            list.add(item)
        }

        sAdapter = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line1, R.id.line2, R.id.line3, R.id.line4, R.id.line5)
        )
        binding.gridviewPackages2.adapter = sAdapter

        binding.gridviewPackages2.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this@BuyMedicineActivity, BuyMedicineDetailsActivity::class.java)
            intent.putExtra("text1", medicinesList[i][0])
            intent.putExtra("text2", medicineDetailsList[i])
            intent.putExtra("text3", medicinesList[i][4])
            startActivity(intent)
        }

        binding.goToCard2.setOnClickListener {
                val intent = Intent(this,CartBuyMedicineActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun onBackButtonClick9(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}