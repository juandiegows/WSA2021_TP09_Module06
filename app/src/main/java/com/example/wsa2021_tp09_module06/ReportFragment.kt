package com.example.wsa2021_tp09_module06

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wsa2021_tp09_module06.adapters.RelatosRecyAdapter
import com.example.wsa2021_tp09_module06.databinding.FragmentReportBinding
import com.example.wsa2021_tp09_module06.databinding.FragmentVisualizarBinding

class ReportFragment : Fragment() {

    companion object {
        fun newInstance() = ReportFragment()
    }

    private lateinit var viewModel: ReportViewModel
    lateinit var binding: FragmentReportBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(layoutInflater)

        var act = this.requireActivity() as PrincipalActivity
        act.binding.appBarPrincipal.imgTree.visibility = View.VISIBLE
        return binding.root
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            binding.recyRelato.layoutManager =GridLayoutManager(this@ReportFragment.requireActivity(),2)
        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            binding.recyRelato.layoutManager =
                LinearLayoutManager(this@ReportFragment.requireActivity()).apply {

                }
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReportViewModel::class.java)
        viewModel.lista.observe(this@ReportFragment.requireActivity()) {
            if(this@ReportFragment.requireActivity().resources.configuration.orientation== Configuration.ORIENTATION_LANDSCAPE){
                binding.recyRelato.layoutManager =GridLayoutManager(this@ReportFragment.requireActivity(),2)
            } else if(this@ReportFragment.requireActivity().resources.configuration.orientation== Configuration.ORIENTATION_PORTRAIT) {
                binding.recyRelato.layoutManager =
                    LinearLayoutManager(this@ReportFragment.requireActivity())
            }
            binding.recyRelato.adapter = RelatosRecyAdapter(this@ReportFragment.requireActivity(),it)
        }
        viewModel.getRelatos()
        // TODO: Use the ViewModel
    }

}