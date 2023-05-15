package com.farm.field

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.farm.R
import com.farm.domain.FieldDetail
import com.farm.domain.FieldHandler
import com.farm.utlis.AreaUnitConverter
import kotlinx.coroutines.runBlocking

class FieldListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_field_list, container, false)
        val rvContacts = view.findViewById<View>(R.id.rvFields) as RecyclerView
        val fieldHandler = FieldHandler()
        val fields : ArrayList<FieldDetail>
        runBlocking {
            fields = fieldHandler.fetchAllFields(context)
        }
        fields.add(FieldDetail("Razem: ",sumFieldArea(fields),"ha"))
        val adapter = FieldAdapter(fields,this)
        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(context)
        return view
    }

    private fun sumFieldArea(fields : List<FieldDetail>) : Float{
        var totalArea = 0f
        for(field in fields){
            val fieldArea :Float = field.area
            val squareMeters : Float = AreaUnitConverter.unitToSquareMeter(field.unit)
            totalArea += fieldArea.times(squareMeters)
        }
        return AreaUnitConverter.squareMeterToHectare(totalArea)
    }

}