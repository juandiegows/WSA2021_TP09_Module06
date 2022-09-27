package com.example.wsa2021_tp09_module06.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

fun IsConected(context: Context): Boolean {
    val conn = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val netInfo = conn!!.activeNetworkInfo
    return  netInfo != null && netInfo.isConnected

}
@RequiresApi(Build.VERSION_CODES.M)
fun isNetworkAvailable(context: Context) =
    (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
        getNetworkCapabilities(activeNetwork)?.run {
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } ?: false
    }