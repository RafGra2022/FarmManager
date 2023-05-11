package com.farm.field

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.farm.R

class FieldActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field)
        if(intent.getStringExtra("fragment").equals("FieldListFragment")){
            supportFragmentManager.beginTransaction().setReorderingAllowed(true).add(R.id.field_action_container, FieldListFragment(), "fieldList").commit()
        }else {
            supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                .add(R.id.field_action_container, AddFieldFragment(), "addField").commit()
        }
    }

}