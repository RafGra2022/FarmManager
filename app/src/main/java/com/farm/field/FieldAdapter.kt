package com.farm.field

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.farm.R
import com.farm.domain.FieldDetail

class FieldAdapter(private val fields: List<FieldDetail>, val parentFragment: Fragment) :
    RecyclerView.Adapter<FieldAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fieldName: TextView = itemView.findViewById(R.id.field_name)
        val area: TextView = itemView.findViewById(R.id.field_area)
        val detailsView: TextView = itemView.findViewById(R.id.details_view)
        val detailsButton: LinearLayout = itemView.findViewById(R.id.go_to_details)
        val fieldContainer: LinearLayout = itemView.findViewById(R.id.container)

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
        holder.detailsView.setOnClickListener(showDetails())
        if(holder.fieldName.text.toString().equals("Razem: ")){
            holder.detailsButton.visibility = View.GONE
            holder.fieldContainer.setBackgroundResource(R.drawable.field_detail_btn_bg)
        }
    }

    private fun showDetails(): View.OnClickListener {
        return View.OnClickListener {
            val navHostFragment = parentFragment.activity?.supportFragmentManager?.findFragmentById(R.id.field_action_container) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.go_to_field_details)
        }
    }

    override fun getItemCount(): Int {
        return fields.size
    }

}