package com.sudansh.savic.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sudansh.savic.Prefs
import com.sudansh.savic.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var rv: RecyclerView
    private lateinit var add: FloatingActionButton
    private val patientAdapter by lazy { PatientAdapter() }
    private val prefs by lazy { Prefs(requireContext()) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        rv = root.findViewById(R.id.rv_patients)
        add = root.findViewById(R.id.fab)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.adapter = patientAdapter

        add.setOnClickListener {
            if (prefs.maxPatient ?: 0 <= patientAdapter.itemCount) {
                Toast.makeText(requireContext(), "Max patient reached", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val fullname = name.editText?.text.toString()
            val age = age.editText?.text.toString()
            val email = email.editText?.text.toString()


            val selectedId: Int = radio_group.checkedRadioButtonId
            val radioButton = view.findViewById(selectedId) as? RadioButton

            val gender = radioButton?.text.toString().first().toString().toUpperCase()
            patientAdapter.updatelist(
                Patient(
                    fullname,
                    age = try {
                        age.toInt()
                    } catch (e: Exception) {
                        0
                    },
                    email = email,
                    gender = gender
                )
            )

        }
    }
}

