package com.example.androidmagicguild

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pet_row.view.*

class PetRecAdapter : RecyclerView.Adapter<PetViewHolder>() {

    //num of items
    override fun getItemCount(): Int {
        return 4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.pet_row, parent, false)
        return PetViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.view.petNameTextView.text = "Bark Twain"
        holder.view.petImageView.setImageResource(R.drawable.ic_launcher_background)
    }
}


class PetViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}