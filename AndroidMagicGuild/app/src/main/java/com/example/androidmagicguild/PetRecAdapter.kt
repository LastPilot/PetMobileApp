package com.example.androidmagicguild

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pet_row.view.*

class PetRecAdapter : RecyclerView.Adapter<PetViewHolder>() {

    //num of items
    override fun getItemCount(): Int {
        return petAdapter.arraySize()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.pet_row, parent, false)
        return PetViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        var pet = petAdapter.returnArray()[position]
        holder.view.petNameTextView.text = pet?.name
        petAdapter.setImageOnView(pet?.name, holder.view.petImageView)
    }
}


class PetViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}