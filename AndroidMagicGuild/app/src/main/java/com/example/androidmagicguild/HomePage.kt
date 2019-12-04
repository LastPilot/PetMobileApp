package com.example.androidmagicguild


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.androidmagicguild.databinding.FragmentHomePageBinding


class HomePage : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHomePageBinding>(
            inflater,
            R.layout.fragment_home_page,
            container,
            false
        )

        binding.adoptPageButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homePage_to_petRecyclerFragment)
        }

        return binding.root
    }
}