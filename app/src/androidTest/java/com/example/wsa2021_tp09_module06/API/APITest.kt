package com.example.wsa2021_tp09_module06.API

import android.telecom.Call
import android.util.Log
import android.widget.Toast
import com.example.wsa2021_tp09_module06.helper.IServices
import com.example.wsa2021_tp09_module06.helper.IServices.Get
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import  okhttp3.Callback
import okhttp3.Response
import org.junit.Assert
import java.io.IOException
import  org.junit.Assert.*;

@RunWith(JUnit4::class)
class APITest {

    @Test
    fun whenTheAPIIsConneted() {

        var response = IServices.okhttp.newCall(Get("api/Test")).execute()
        Log.e("TAG", "whenTheAPIIsConneted:${response.body!!.string()} " )
        Assert.assertTrue(response.isSuccessful)
    }


}