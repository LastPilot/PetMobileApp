package com.example.androidmagicguild


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmagicguild.databinding.FragmentPetRecyclerBinding


/**
 * A simple [Fragment] subclass.
 */
class PetRecyclerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentPetRecyclerBinding>(
            inflater,
            R.layout.fragment_pet_recycler,
            container,
            false
        )
        val recyclerViewPet = binding.recyclerForPet as RecyclerView
        recyclerViewPet.layoutManager = LinearLayoutManager(activity)
        recyclerViewPet.adapter = PetRecAdapter()

        binding.backToHomeButoon.setOnClickListener{view: View->
            view.findNavController().navigate(R.id.action_petRecyclerFragment_to_homePage)
        }

        return binding.root
    }


}