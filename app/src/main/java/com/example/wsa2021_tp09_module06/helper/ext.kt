package com.example.wsa2021_tp09_module06.helper

import android.util.Log
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputEditText.Requerido(layout: TextInputLayout) {
    this.setOnFocusChangeListener { v, hasFocus ->

        if (hasFocus) {
            layout.error = ""
        } else {
            if (this.text.toString().isEmpty()) {
                layout.error = "this field is required"
            }
        }

    }

}
var TextInputEditText.textJD:String
    get() {
       return this.text.toString()
    }
    set(value) {
        this.setText(value)
    }

fun ArrayList<View>.IsValido():Boolean {
    var isvalido = true
    this.forEach {
        it.requestFocus()
        it.clearFocus()
        if (it is TextInputLayout) {
            Log.e("TAG", "IsValido: ${it.error.toString()}" )
            if (!it.error.isNullOrEmpty()) {
                isvalido = false
            }
        }
    }
    return isvalido
}

