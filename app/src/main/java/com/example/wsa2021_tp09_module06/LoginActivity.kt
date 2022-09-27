package com.example.wsa2021_tp09_module06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.wsa2021_tp09_module06.databinding.ActivityLoginBinding
import com.example.wsa2021_tp09_module06.helper.IsValido
import com.example.wsa2021_tp09_module06.helper.Requerido

class LoginActivity : AppCompatActivity() {
    override fun onBackPressed() {
        Toast.makeText(this, "not permitted to go back", Toast.LENGTH_SHORT).show()
    }


    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Load()
    }

    fun viewToValid():java.util.ArrayList<View> = with(binding) {
        return@with arrayListOf<View>(txtSuser, txtSpass)
    }
    fun Load() {

        with(binding) {
            txtPass.Requerido(txtSpass)
            txtuser.Requerido(txtSuser)
            btnLogin.setOnClickListener {
                Toast.makeText(this@LoginActivity, "it was clicked",Toast.LENGTH_SHORT).show()
                Validar()
            }
        }
    }

    fun Validar(): Boolean {
        with(binding) {
            var lista = arrayListOf<View>(txtSuser, txtSpass)
            return lista.IsValido()
        }

    }
}