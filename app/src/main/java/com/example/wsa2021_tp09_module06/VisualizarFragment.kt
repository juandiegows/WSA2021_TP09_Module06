package com.example.wsa2021_tp09_module06

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wsa2021_tp09_module06.databinding.FragmentVisualizarBinding

class VisualizarFragment : Fragment() {

    companion object {
        fun newInstance() = VisualizarFragment()
    }

    private lateinit var viewModel: VisualizarViewModel
lateinit var  binding: FragmentVisualizarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVisualizarBinding.inflate(layoutInflater)
        var act = this.requireActivity() as PrincipalActivity
        act.binding.appBarPrincipal.imgTree.visibility = View.VISIBLE
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VisualizarViewModel::class.java)
        // TODO: Use the ViewModel
    }

}