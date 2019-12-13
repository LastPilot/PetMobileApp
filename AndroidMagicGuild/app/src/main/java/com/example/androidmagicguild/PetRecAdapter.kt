package com.example.androidmagicguild

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pet_row.view.*

class PetRecAdapter : RecyclerView.Adapter<PetRecAdapter.PetViewHolder>() {

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
        holder.view.petBioTextView.text = pet?.bio
        holder.view.petTypeView.text = pet?.type
        holder.view.petContactTextView.text = pet?.contact
        petAdapter.setImageOnView(pet?.name, holder.view.petImageView)


        holder.view.adoptThatBoi.setOnClickListener {
            holder.view.petTypeView.visibility = View.VISIBLE
            holder.view.typeView.visibility = View.VISIBLE
            holder.view.bioTextView.visibility = View.VISIBLE
            holder.view.petBioTextView.visibility = View.VISIBLE
            holder.view.contactTextView.visibility = View.VISIBLE
            holder.view.petContactTextView.visibility = View.VISIBLE
            holder.view.adoptThatBoi.visibility = View.GONE
            holder.view.backCollapse.visibility = View.VISIBLE
            notifyDataSetChanged()

        }
        holder.view.backCollapse.setOnClickListener {
            holder.view.petTypeView.visibility = View.GONE
            holder.view.typeView.visibility = View.GONE
            holder.view.bioTextView.visibility = View.GONE
            holder.view.petBioTextView.visibility = View.GONE
            holder.view.contactTextView.visibility = View.GONE
            holder.view.petContactTextView.visibility = View.GONE
            holder.view.backCollapse.visibility = View.GONE
            holder.view.adoptThatBoi.visibility = View.VISIBLE
            notifyDataSetChanged()
        }


    }

    class PetViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }
}