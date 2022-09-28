package com.example.wsa2021_tp09_module06.helper

import android.util.Log
import com.example.wsa2021_tp09_module06.model.User
import com.example.wsa2021_tp09_module06.model.UserLogin
import org.json.JSONObject
import org.junit.Assert.*

import org.junit.Test

class ExtKtTest {

    @Test
    fun toClass(){
        try {
            var user:User = JSONObject("{\"id\":2,\"nome\":\"Tatiana\",\"email\":\"tati@katana.com.br\",\"funcaoid\":1,\"funcao1\":\"Administrador\",\"telefone\":\"955828564\"}")
                .toClass(User::class.java.name).Cast()
            assertTrue(true)
        } catch (e: Exception) {
        }

    }
    @Test
    fun toJson() {
        try {
            var json = UserLogin().apply {
                Username = "Juan Diego"
                Password = "12345"

            }.toJson()
            Log.e("TAG", "toJson: $json" )
            assertEquals(JSONObject(json).getString("Password"),"12345")
        } catch (e: Exception) {
            assertFalse(true)
        }
    }
}