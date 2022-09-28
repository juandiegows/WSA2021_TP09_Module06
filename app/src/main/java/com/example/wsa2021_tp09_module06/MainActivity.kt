package com.example.wsa2021_tp09_module06

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.view.*
import com.example.wsa2021_tp09_module06.helper.IServices
import com.example.wsa2021_tp09_module06.helper.toJson
import com.example.wsa2021_tp09_module06.model.UserLogin
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ScreenFull()
        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this,LoginActivity::class.java))
        },5000)

        IServices.okhttp.newCall(IServices.Get("api/Test")).enqueue(
            object :Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("TAG", "whenTheAPIIsConneted:${e.message.toString()} " )

                }

                override fun onResponse(call: Call, response: Response) {
                    if(response.isSuccessful){
                        Log.e("TAG", "whenTheAPIIsConneted:${response.body!!.string()} " )
                    }else {

                    }


                }
            }
        )
     }

    fun ScreenFull(){
        WindowCompat.setDecorFitsSystemWindows(window,false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            this.hide(WindowInsetsCompat.Type.statusBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}