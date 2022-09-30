package com.example.wsa2021_tp09_module06

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe

class DetalleRelatoFragment : Fragment() {

    companion object {
        fun newInstance() = DetalleRelatoFragment()
    }

    private lateinit var viewModel: DetalleRelatoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detalle_relato, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetalleRelatoViewModel::class.java)
        // TODO: Use the ViewModel

        viewModel.relato.observe(viewLifecycleOwner){

        }
        viewModel.GetRelato()
    }

}