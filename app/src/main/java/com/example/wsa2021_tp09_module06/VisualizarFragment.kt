package com.example.wsa2021_tp09_module06

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class VisualizarFragment : Fragment() {

    companion object {
        fun newInstance() = VisualizarFragment()
    }

    private lateinit var viewModel: VisualizarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_visualizar, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VisualizarViewModel::class.java)
        // TODO: Use the ViewModel
    }

}