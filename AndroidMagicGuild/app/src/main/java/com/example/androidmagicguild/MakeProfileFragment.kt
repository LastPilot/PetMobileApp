package com.example.androidmagicguild

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.example.androidmagicguild.databinding.MakeProfileBinding


class MakeProfileFragment: Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var submit_pet: Button
    private lateinit var pet_type: Spinner

    val petTypes = arrayOf<String>("Dog", "Cat", "Bird", "Reptile", "Other")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: MakeProfileBinding =
            DataBindingUtil.inflate(inflater, R.layout.make_profile, container, false)

        val adapt = ArrayAdapter(view!!.context,android.R.layout.simple_spinner_item,petTypes)

        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        pet_type.adapter = adapt

        return binding.root
    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        // use position to know the selected item
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }


}
