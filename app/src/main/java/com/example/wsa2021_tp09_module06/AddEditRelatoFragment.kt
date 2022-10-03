package com.example.wsa2021_tp09_module06

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AddEditRelatoFragment : Fragment() {

    companion object {
        fun newInstance() = AddEditRelatoFragment()
    }

    private lateinit var viewModel: AddEditRelatoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_edit_relato, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddEditRelatoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}