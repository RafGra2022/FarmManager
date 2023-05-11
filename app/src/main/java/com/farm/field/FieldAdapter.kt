package com.farm.field

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.farm.R
import com.farm.domain.FieldDetail

class FieldAdapter(private val fields: List<FieldDetail>) :
    RecyclerView.Adapter<FieldAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fieldName = itemView.findViewById<TextView>(R.id.field_name)
        val area = itemView.findViewById<TextView>(R.id.field_area)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val fieldView = inflater.inflate(R.layout.adapter_field_item, parent, false)
        return ViewHolder(fieldView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fieldDetail = fields.get(position)
        val fieldName = holder.fieldName
        fieldName.setText(fieldDetail.field)
        val area = holder.area
        area.setText(fieldDetail.area.toString() + " " + fieldDetail.unit)
    }

    override fun getItemCount(): Int {
        return fields.size
    }
}