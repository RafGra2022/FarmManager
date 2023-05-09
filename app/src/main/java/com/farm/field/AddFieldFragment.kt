package com.farm.field

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import com.farm.R


class AddFieldFragment : Fragment() {

    private val units = arrayOf("ha","ar","m2")
    private var area : AutoCompleteTextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_field, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayAdapter : ArrayAdapter<String> = ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line, units)
        area = view.findViewById(R.id.area_unit)
        area?.setAdapter(arrayAdapter)
        area?.setOnClickListener(showUnitList())
        view.findViewById<Button>(R.id.add_field).setOnClickListener(addField())
    }

    private fun addField(): View.OnClickListener {
        return View.OnClickListener {
            val fieldName = view?.findViewById<EditText>(R.id.field_name)
            val areaSize = view?.findViewById<EditText>(R.id.area_size)
            val areaUnit = view?.findViewById<AutoCompleteTextView>(R.id.area_unit)

        }
    }

    private fun showUnitList(): View.OnClickListener {
        return View.OnClickListener{
            area?.showDropDown()
        }
    }

}