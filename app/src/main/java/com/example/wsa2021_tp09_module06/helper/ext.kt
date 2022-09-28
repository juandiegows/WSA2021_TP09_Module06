package com.example.wsa2021_tp09_module06.helper

import android.content.Context
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONArray
import org.json.JSONObject
import kotlin.reflect.javaType
import kotlin.reflect.typeOf


@OptIn(ExperimentalStdlibApi::class)
fun JSONObject.toClass(nameClass:String): Any {
    var OnClass = Class.forName(nameClass)

    var instance = OnClass.newInstance()
    OnClass.declaredFields.forEach {
        it.isAccessible = true
        it.set(
            instance, when (it.type) {
                typeOf<Int>().javaType -> this.getInt(it.name)
                typeOf<Long>().javaType -> this.get(it.name) as Long
                typeOf<String>().javaType -> this.getString(it.name)
                typeOf<Double>().javaType -> this.getDouble(it.name)
                typeOf<Boolean>().javaType -> this.getBoolean(it.name)
                else -> {
                    try {
                        this.getJSONObject(it.name).toClass(it.type::class.java.name)
                    } catch (e: Exception) {
                        this.getJSONArray(it.name).toList(it.type::class.java.name)
                    }
                }
            }
        )

    }
    return instance;
}

fun JSONArray.toList(nameClass: String): ArrayList<Any> {
    var list = ArrayList<Any>()
    for (i in 0 until this.length()) {

        list.add(this.getJSONObject(i).toClass(nameClass))
    }
    return  list
}

fun TextInputEditText.Requerido(layout: TextInputLayout) {
    this.setOnFocusChangeListener { _, hasFocus ->

        if (hasFocus) {
            layout.error = ""
        } else {
            if (this.text.toString().isEmpty()) {
                layout.error = "this field is required"
            }
        }

    }

}

var TextInputEditText.textJD: String
    get() {
        return this.text.toString()
    }
    set(value) {
        this.setText(value)
    }

fun ArrayList<View>.IsValido(): Boolean {
    var isvalido = true
    this.forEach {
        it.requestFocus()
        it.clearFocus()
        if (it is TextInputLayout) {
            Log.e("TAG", "IsValido: ${it.error.toString()}")
            if (!it.error.isNullOrEmpty()) {
                isvalido = false
            }
        }
    }
    return isvalido
}

fun Any.toJson(): String {
    var json = JSONObject()
    var onClass = this::class.java
    onClass.declaredFields.forEach {
        it.isAccessible = true
        json.put(it.name, it.get(this))
    }
    return json.toString()
}

inline fun <reified T> Any.Cast(): T {
    return this as T;
}

fun Context.AlertJD(message: String, method: () -> Unit) {
    AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton("Ok") { _, _ ->
            method.invoke()
        }
        .create()
        .show()
}

