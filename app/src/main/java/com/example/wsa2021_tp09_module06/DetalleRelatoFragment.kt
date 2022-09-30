package com.example.wsa2021_tp09_module06

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.observe
import com.example.wsa2021_tp09_module06.databinding.FragmentDetalleRelatoBinding
import com.example.wsa2021_tp09_module06.helper.Singleton

class DetalleRelatoFragment : Fragment() {

    companion object {
        fun newInstance() = DetalleRelatoFragment()
    }

    private lateinit var viewModel: DetalleRelatoViewModel
    lateinit var binding: FragmentDetalleRelatoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalleRelatoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onConfigurationChanged(newConfig: Configuration) {

        super.onConfigurationChanged(newConfig)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetalleRelatoViewModel::class.java)
        // TODO: Use the ViewModel
        var activity = requireActivity() as PrincipalActivity
        Singleton.typeMenu = Singleton.menu.menuDetails
        activity.invalidateMenu()
        viewModel.relato.observe(viewLifecycleOwner) {
            with(binding) {
                txtDescription.setText(it.relato)
                txtlatitu.setText(it.latitude.toString())
                txtlongitu.setText(it.longitude.toString())
                txtAutor.setText(it.nome)
                txtTelAutor.setText(it.telefone)
                txtEmailAutor.setText(it.email)
                if (txtAutor.text.toString() == "null") {
                    txtAutor.setText("anonimo")
                    txtTelAutor.setText("anonimo")
                    txtEmailAutor.setText("anonimo")
                }
                var url = requireContext().resources.getIdentifier(
                    "@drawable/v${it.imagem.replace(".jgp", "")}",
                    null,
                    requireContext().packageName
                )
                imgPhoto.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        url
                    )
                )
            }
        }
        viewModel.GetRelato()
    }

}