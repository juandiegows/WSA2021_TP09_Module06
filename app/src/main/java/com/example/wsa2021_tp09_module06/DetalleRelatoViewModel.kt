package com.example.wsa2021_tp09_module06

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wsa2021_tp09_module06.helper.Singleton
import com.example.wsa2021_tp09_module06.model.Relatos

class DetalleRelatoViewModel : ViewModel() {
    private var _relato = MutableLiveData(Singleton.relatos)
    var relato: LiveData<Relatos> = _relato

    fun GetRelato(){
        _relato.postValue(Singleton.relatos)
    }
}