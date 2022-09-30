package com.example.wsa2021_tp09_module06

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wsa2021_tp09_module06.helper.Cast
import com.example.wsa2021_tp09_module06.helper.IServices
import com.example.wsa2021_tp09_module06.helper.IServices.Get
import com.example.wsa2021_tp09_module06.helper.toList
import com.example.wsa2021_tp09_module06.model.Relatos
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException

class ReportViewModel : ViewModel() {


    private val _lista = MutableLiveData(arrayListOf<Relatos>())
    var lista:LiveData<ArrayList<Relatos>> = _lista


     @Throws(IOException::class, java.lang.Exception::class)
    fun getRelatos() {
        IServices.okhttp.newCall(Get("api/relatos")).enqueue(
            object : Callback{

                override fun onFailure(call: Call, e: IOException) {
                    throw  e
                }

                override fun onResponse(call: Call, response: Response) {
                    var responseJD = response.body!!.string()
                    Log.e("TAG", "onResponse: $responseJD " )
                    try {

                        _lista.postValue( JSONArray(responseJD).toList(Relatos::class.java.name).Cast());
                    } catch (e: Exception) {
                        throw  e
                    }
                }
            }
        )
    }

}