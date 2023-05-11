package com.farm.field

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.farm.MainActivity
import com.farm.R
import com.farm.domain.FieldDetail
import com.farm.domain.FieldHandler
import com.farm.utlis.SystemTimeStamp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddFieldFragment : Fragment() {

    private val units = arrayOf("ha", "ar", "m2")
    private var area: AutoCompleteTextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_field, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, units)
        area = view.findViewById(R.id.area_unit)
        area?.setAdapter(arrayAdapter)
        area?.setOnClickListener(showUnitList())
        view.findViewById<Button>(R.id.add_field_btn).setOnClickListener(addField())
    }

    private fun addField(): View.OnClickListener {
        return View.OnClickListener {
            val fieldName = view?.findViewById<EditText>(R.id.field_name)?.text.toString()
            val areaSize = view?.findViewById<EditText>(R.id.area_size)?.text.toString().toFloat()
            val areaUnit = view?.findViewById<AutoCompleteTextView>(R.id.area_unit)?.text.toString()
            val fieldDetail = FieldDetail(fieldName, areaSize, areaUnit)
            val fieldHandler = FieldHandler()

            fieldHandler.save(context, fieldDetail)
            Toast.makeText(context, "Działka zapisana", Toast.LENGTH_LONG).show()
            startActivity(Intent(context,MainActivity::class.java))
        }
    }

    private fun showUnitList(): View.OnClickListener {
        return View.OnClickListener {
            area?.showDropDown()
        }
    }

}