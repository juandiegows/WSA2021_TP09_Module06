package com.example.wsa2021_tp09_module06.helper

import com.example.wsa2021_tp09_module06.model.Relatos
import com.example.wsa2021_tp09_module06.model.User

object Singleton {

    var UserLogin:User = User().apply {
        funcaoid =2
    }
    var relatos:Relatos = Relatos()
    fun GetUser():User{
        return  UserLogin.apply {
            funcaoid = 2
        }
    }
}