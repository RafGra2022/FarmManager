package com.farm.field

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.farm.R
import com.farm.domain.FieldDetail
import com.farm.domain.FieldHandler
import kotlinx.coroutines.runBlocking

class FieldListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_field_list, container, false)
        val rvContacts = view.findViewById<View>(R.id.rvFields) as RecyclerView
        val fieldHandler = FieldHandler()
        val fields : List<FieldDetail>
        runBlocking {
            fields = fieldHandler.fetchAllFields(context)
        }

        val adapter = FieldAdapter(fields)
        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(context)
        return view
    }

}