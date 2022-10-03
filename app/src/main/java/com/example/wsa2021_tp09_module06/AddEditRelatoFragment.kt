package com.example.wsa2021_tp09_module06

import android.Manifest
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.wsa2021_tp09_module06.databinding.FragmentAddEditRelatoBinding

class AddEditRelatoFragment : Fragment() {

    companion object {
        fun newInstance() = AddEditRelatoFragment()
    }

    lateinit var binding: FragmentAddEditRelatoBinding
    private lateinit var viewModel: AddEditRelatoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddEditRelatoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddEditRelatoViewModel::class.java)
        // TODO: Use the ViewModel
        getPermissions.launch(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private val getPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            var size = it.filter { x -> x.value }.size
            Toast.makeText(
                this.requireContext(),
                "$size/${it.size} permissions granted",
                Toast.LENGTH_LONG
            ).show()
        }

}