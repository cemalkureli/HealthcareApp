package com.example.healthcare.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.healthcare.Database.Database
import com.example.healthcare.R

class LineItemAdapter(
    private val context: Context,
    private val items: MutableList<HashMap<String, String>>,
    private val orderDetails: MutableList<Array<String?>>
) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.multi_lines2, parent, false)
        } else {
            view = convertView
        }

        val line1TextView = view.findViewById<TextView>(R.id.line1)
        val line2TextView = view.findViewById<TextView>(R.id.line2)
        val line3TextView = view.findViewById<TextView>(R.id.line3)
        val line4TextView = view.findViewById<TextView>(R.id.line4)
        val line5TextView = view.findViewById<TextView>(R.id.line5)
        val deleteButton = view.findViewById<Button>(R.id.deleteOrder)

        val item = items[position]
        line1TextView.text = item["line1"]
        line2TextView.text = item["line2"]
        line3TextView.text = item["line3"]
        line4TextView.text = item["line4"]
        line5TextView.text = item["line5"]

        deleteButton.setOnClickListener {
            val order = orderDetails[position]
            val username = order[0].toString()
            val type = order[5].toString()
            val product = order[6].toString()


            val database = Database(context)
            database.removeOrder(username, product, type)

            items.removeAt(position)
            orderDetails.removeAt(position)
            notifyDataSetChanged()
        }

        return view
    }
}

