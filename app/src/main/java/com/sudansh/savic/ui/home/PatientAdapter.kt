package com.sudansh.savic.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sudansh.savic.R

class PatientAdapter :
    RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    private var patientlist = mutableListOf<Patient>()

    // Describes an item view and its place within the RecyclerView
    class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val flowerTextView: TextView = itemView.findViewById(R.id.patient_text)

        fun bind(index: Int, patient: Patient) {
            flowerTextView.text = "$index $patient"
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_patient, parent, false)

        return PatientViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount(): Int = patientlist.size

    // Displays data at a certain position
    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.bind(position + 1, patientlist[position])
    }

    fun updatelist(item: Patient) {
        patientlist.add(item)
        notifyDataSetChanged()
    }
}