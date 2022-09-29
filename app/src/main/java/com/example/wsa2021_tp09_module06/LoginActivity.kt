package com.example.wsa2021_tp09_module06

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.wsa2021_tp09_module06.databinding.ActivityLoginBinding
import com.example.wsa2021_tp09_module06.helper.*
import com.example.wsa2021_tp09_module06.helper.IServices.Post
import com.example.wsa2021_tp09_module06.model.User
import com.example.wsa2021_tp09_module06.model.UserLogin
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    var intento = 0
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

    fun viewToValid(): java.util.ArrayList<View> = with(binding) {
        return@with arrayListOf<View>(txtSuser, txtSpass)
    }

    fun allViewValidator() = with(binding) {
        return@with arrayListOf<View>(txtSuser, txtSpass, btnLogin)
    }

    fun Load() {

        with(binding) {
            txtPass.Requerido(txtSpass)
            txtuser.Requerido(txtSuser)
            btnLogin.setOnClickListener {
                Login()
            }
        }
    }

    private fun Login() {

        if (Validar()) {

            setVisibleProgressBar()
            IServices.okhttp.newCall(
                Post(
                    "api/auth",
                    UserLogin().apply {
                        with(binding) {
                            Username = txtuser.textJD
                            Password = txtPass.textJD
                        }
                    }.toJson()
                        .toRequestBody("application/json".toMediaType())
                )
            ).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        setVisibleButtom()
                        this@LoginActivity.AlertJD("Error in the API ${e.message}") {
                            Toast.makeText(this@LoginActivity, "Error", Toast.LENGTH_SHORT).show()
                        };
                    }

                }

                override fun onResponse(call: Call, response: Response) {
                    runOnUiThread {
                        Log.e("TAG", "onResponse: ${call.request().url}" )
                        setVisibleButtom()
                        if(response.isSuccessful){
                            if(response.code == 200){
                                Singleton.UserLogin =
                                    JSONObject(response.body!!.string())
                                        .getJSONObject("Data")
                                        .toClass(User::class.java.name).Cast()
                                startActivity(Intent(this@LoginActivity, PrincipalActivity::class.java))
                            }
                        }else {
                            if(response.code == 401){
                                this@LoginActivity.AlertJD("User or password incorrect") {  };
                            }
                            Log.e("TAG", "onResponse: ${response.code}" )
                        }
                    }


                }
            })

        } else {
            Toast.makeText(this, "User or password invalid", Toast.LENGTH_SHORT).show()
            intento++;
            if (intento >= 3) {
                Wait30Seconds()
            }

        }
    }

    private fun setVisibleProgressBar() {
        binding.btnLogin.visibility = View.INVISIBLE
        binding.pbLoad.visibility = View.VISIBLE
    }

    private fun setVisibleButtom() {
        binding.btnLogin.visibility = View.VISIBLE
        binding.pbLoad.visibility = View.INVISIBLE
    }

    private fun Wait30Seconds() {
        Blocked()
        Toast.makeText(this, "login blocked,please wait 30 seconds", Toast.LENGTH_SHORT).show()
        object : CountDownTimer(30_000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.txtMessage.setText("please, You wait ${millisUntilFinished / 1000} seconds")
            }

            override fun onFinish() {
                binding.txtMessage.setText("")
                Unlock()
            }


        }.start()
    }

    private fun Unlock() {
        allViewValidator().forEach {
            it.isEnabled = true
        }
    }

    private fun Blocked() {
        allViewValidator().forEach {
            it.isEnabled = false
        }
    }

    fun Validar(): Boolean {
        with(binding) {
            var lista = arrayListOf<View>(txtSuser, txtSpass)
            return lista.IsValido()
        }

    }
}