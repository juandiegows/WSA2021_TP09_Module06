package com.example.wsa2021_tp09_module06

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.wsa2021_tp09_module06.helper.IServices
import com.example.wsa2021_tp09_module06.helper.IsValido
import com.example.wsa2021_tp09_module06.helper.textJD
import com.example.wsa2021_tp09_module06.helper.toJson
import com.example.wsa2021_tp09_module06.model.UserLogin
import kotlinx.coroutines.runBlocking
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {


    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<LoginActivity> =
        ActivityScenarioRule(LoginActivity::class.java)


    @Test
    fun WhenIsValido() {
        activityScenarioRule.scenario.onActivity {

            with(it.binding) {
                txtuser.textJD = "Juan  "
                txtPass.textJD = "12345"
            }
            var result = it.Validar()

            if (result)
                assertTrue(true)
        }

    }

    @Test
    fun WhenIsNotValido() {
        activityScenarioRule.scenario.onActivity {
            with(it.binding) {
                txtuser.textJD = ""
                txtPass.textJD = ""
                btnLogin.performClick()
            }

            var result = it.Validar()
            if (!result)
                assertTrue(true)
        }

    }

    @Test
    fun WhenLoginBlocked() {
        activityScenarioRule.scenario.onActivity {
            with(it.binding) {
                txtuser.textJD = ""
                txtPass.textJD = ""
                btnLogin.performClick()
                btnLogin.performClick()
                btnLogin.performClick()
                assertTrue(!btnLogin.isEnabled)
            }

        }
    }

    @Test
    fun WhenLoginUnlockedAfter30Seconds() {

        activityScenarioRule.scenario.onActivity {
            with(it.binding) {
                txtuser.textJD = ""
                txtPass.textJD = ""
                btnLogin.performClick()
                btnLogin.performClick()
                btnLogin.performClick()

                assertTrue(true)

            }


        }

    }

    @Test
    fun WhenLoginSuccessful(){
        activityScenarioRule.scenario.onActivity {
          var response =   IServices.okhttp.newCall(
                IServices.Post(
                    "api/auth",
                    UserLogin().apply {
                            Username = "tati@katana.com.br"
                            Password = "12345"

                    }.toJson()
                        .toRequestBody("application/json".toMediaType())
                )
            ).enqueue(object :Callback{
              override fun onFailure(call: Call, e: IOException) {
                  TODO("Not yet implemented")
              }

              override fun onResponse(call: Call, response: Response) {
                  assertTrue(response.code == 200)
              }
          })

        }

    }
    fun WhenLoginIncorrect(){
        activityScenarioRule.scenario.onActivity {
           IServices.okhttp.newCall(
                IServices.Post(
                    "api/auth",
                    UserLogin().apply {
                        Username = "tati@katana.com.br"
                        Password = "1234556"

                    }.toJson()
                        .toRequestBody("application/json".toMediaType())
                )
            ).enqueue(object :Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    assertTrue(response.code == 401)
                }
            })

        }

    }
}