package com.farm.field

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
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
        area = view.findViewById(R.id.area)
        area?.setAdapter(arrayAdapter)
        area?.setOnClickListener(showUnitList())
    }

    private fun showUnitList(): View.OnClickListener {
        return View.OnClickListener{
            area?.showDropDown()
        }
    }

}