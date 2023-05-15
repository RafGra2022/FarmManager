package com.farm.field

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.farm.R
import com.google.android.material.button.MaterialButton

class FieldDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_field_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<MaterialButton>(R.id.agricultural_treatment).setOnClickListener(showAgriculture())
    }

    private fun showAgriculture(): View.OnClickListener? {
        return View.OnClickListener {
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.field_action_container) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.go_to_agriculture)
        }
    }

}