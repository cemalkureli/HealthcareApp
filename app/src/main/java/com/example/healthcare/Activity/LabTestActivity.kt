package com.example.healthcare.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.SimpleAdapter
import com.example.healthcare.Database.PackagesAndDetails
import com.example.healthcare.R
import com.example.healthcare.databinding.ActivityLabTestBinding

class LabTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLabTestBinding
    private lateinit var sAdapter: SimpleAdapter
    val packagesLab: ArrayList<Array<String>> = ArrayList()
    val packageDetailsLab: ArrayList<String> = ArrayList()

    val packagesAndDetails = PackagesAndDetails()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLabTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (i in packagesAndDetails.packages.indices) {
            packagesLab.add(packagesAndDetails.packages[i])
            packageDetailsLab.add(packagesAndDetails.packageDetails[i])
        }

        val list: ArrayList<HashMap<String, String>> = ArrayList()
        for (i in packagesLab.indices) {
            val item: HashMap<String, String> = HashMap()
            item["line1"] = packagesLab[i][0]
            item["line2"] = packagesLab[i][1]
            item["line3"] = packagesLab[i][2]
            item["line4"] = packagesLab[i][3]
            item["line5"] = "Total Cost: ${packagesLab[i][4]}₺/-"
            list.add(item)
        }

        sAdapter = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line1, R.id.line2, R.id.line3, R.id.line4, R.id.line5)
        )
        binding.gridviewPackages.adapter = sAdapter

        binding.gridviewPackages.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this@LabTestActivity, LabTestDetailsActivity::class.java)
            intent.putExtra("text1", packagesLab[i][0])
            intent.putExtra("text2", packageDetailsLab[i])
            intent.putExtra("text3", packagesLab[i][4])
            startActivity(intent)
        }

        binding.goToCard.setOnClickListener {
            val intent = Intent(this,CartLabTestActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun onBackButtonClick5(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
