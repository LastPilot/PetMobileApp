package com.example.androidmagicguild

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.example.androidmagicguild.databinding.MakeProfileBinding


class MakeProfileFragment: Fragment() {
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

        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,petTypes)



        return binding.root
    }



}
