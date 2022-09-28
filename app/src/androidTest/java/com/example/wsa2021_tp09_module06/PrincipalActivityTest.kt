package com.example.wsa2021_tp09_module06

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.wsa2021_tp09_module06.databinding.NavHeaderPrincipalBinding
import com.example.wsa2021_tp09_module06.helper.Cast
import com.example.wsa2021_tp09_module06.helper.Singleton
import com.example.wsa2021_tp09_module06.model.User
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PrincipalActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<PrincipalActivity> =
        ActivityScenarioRule(PrincipalActivity::class.java)

    @Test
    fun WhenShowInfoUserNavDrawer() {
        Singleton.UserLogin = User().apply {
            this.nome = "Juan Diego"
            this.email = "Mejimaestrejuandiego@gmail.com"
        }
        activityRule.scenario.onActivity {

            with(it.binding) {
                var headerJD = NavHeaderPrincipalBinding.bind(navView.getHeaderView(0))
                assertEquals(headerJD.txtName.text.toString(), "Juan Diego")
            }
        }
    }

}