package com.example.wsa2021_tp09_module06.helper

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.util.concurrent.TimeUnit

object IServices {


    val okhttp = OkHttpClient.Builder()
        .callTimeout(3, TimeUnit.MINUTES)
        .writeTimeout(3, TimeUnit.MINUTES)
        .readTimeout(3, TimeUnit.MINUTES)
        .build()
    val URL = "http://10.0.2.2:3141"

    fun Get(address:String):Request = Request.Builder().url("$URL/$address").get().build()
    fun Post(address:String, body: RequestBody):Request = Request.Builder().url("$URL/$address").post(body).build()
    fun Put(address:String, body: RequestBody):Request = Request.Builder().url("$URL/$address").put(body).build()

}