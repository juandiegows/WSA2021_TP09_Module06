package com.example.wsa2021_tp09_module06

import android.content.Intent
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.wsa2021_tp09_module06.helper.IsValido
import com.example.wsa2021_tp09_module06.helper.textJD
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {


    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<LoginActivity> =
        ActivityScenarioRule<LoginActivity>(LoginActivity::class.java)


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
            else
                assertFalse(true)
        }

    }


    @Test
    fun WhenIsNotValido() {
        activityScenarioRule.scenario.onActivity {

            with(it.binding) {
                txtuser.textJD = ""
                txtPass.textJD = "12345"
            }
            var result = it.Validar()
            if (!result)
                assertTrue(true)
            else
                assertFalse(true)
        }

    }
}