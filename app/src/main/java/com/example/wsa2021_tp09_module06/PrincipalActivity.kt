package com.example.wsa2021_tp09_module06

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.wsa2021_tp09_module06.databinding.ActivityPrincipalBinding
import com.example.wsa2021_tp09_module06.databinding.AppBarPrincipalBinding
import com.example.wsa2021_tp09_module06.databinding.ConfirmYesNoBinding
import com.example.wsa2021_tp09_module06.databinding.NavHeaderPrincipalBinding
import com.example.wsa2021_tp09_module06.helper.AlertJD
import com.example.wsa2021_tp09_module06.helper.Cast
import com.example.wsa2021_tp09_module06.helper.IServices
import com.example.wsa2021_tp09_module06.helper.Singleton
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class PrincipalActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityPrincipalBinding
    override fun onBackPressed() {
        Toast.makeText(this, "not permitted to go back", Toast.LENGTH_LONG).show()
    }

    fun OcultarTree(context: Activity) {
        var bindingLocal = context.findViewById<View>(R.id.imgTree)
        bindingLocal.visibility = View.GONE;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarPrincipal.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout

        val navView: NavigationView = binding.navView
        var headerJD = NavHeaderPrincipalBinding.bind(navView.getHeaderView(0))
        headerJD.txtName.text = Singleton.UserLogin.nome
        headerJD.textView.text = Singleton.UserLogin.email
        if (Singleton.GetUser().funcaoid != 1)
            navView.menu.findItem(R.id.visualizarFragment).isEnabled = false

        navView.menu.findItem(R.id.itemExit).setOnMenuItemClickListener {
            drawerLayout.closeDrawer(navView)
            AlertDialog.Builder(this)
                .setTitle("logout")
                .setMessage("Are you sure, I want to logout?")
                .setPositiveButton("yes") { _, _ ->
                    this.finish()
                }
                .setNegativeButton("Cancel") { _, _ ->
                }
                .create()
                .show()

            return@setOnMenuItemClickListener true
        }

        val navController = findNavController(R.id.nav_host_fragment_content_principal)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.visualizarFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (Singleton.typeMenu === Singleton.menu.normal)
            menuInflater.inflate(R.menu.principal, menu)
        if (Singleton.typeMenu === Singleton.menu.menuDetails)
            menuInflater.inflate(R.menu.menu_details, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            menu?.findItem(R.id.itemDelete)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
            menu?.findItem(R.id.Item_Edit)?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemInfo -> {
                this.AlertJD("V1.0.0 Desenvolvido por Juan Diego <DR> WSA 2021.") {

                }
            }
            R.id.Item_Edit -> {
                Navigation.findNavController(this, R.id.nav_host_fragment_content_principal)
                    .navigate(R.id.addEditRelatoFragment)
            }
            R.id.itemDelete -> {
                var bindingAlert = ConfirmYesNoBinding.bind(
                    LayoutInflater.from(this)
                        .inflate(R.layout.confirm_yes_no, null, false)
                )


                var aler = AlertDialog.Builder(this)
                    .setView(bindingAlert.root)
                    .create()
                aler.show()
                bindingAlert.btnNo.setOnClickListener {
                    aler.hide()
                }
                bindingAlert.btnYes.setOnClickListener {

                    aler.hide()
                    IServices.okhttp.newCall(IServices.Delete("api/relatos/${Singleton.relatos.id}"))
                        .enqueue(

                            object : Callback {
                                override fun onFailure(call: Call, e: IOException) {
                                    TODO("Not yet implemented")
                                }

                                override fun onResponse(call: Call, response: Response) {

                                    this@PrincipalActivity.runOnUiThread {
                                        Toast.makeText(
                                            this@PrincipalActivity,
                                            "has been removed successful",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        Navigation.findNavController(
                                            this@PrincipalActivity,
                                            R.id.nav_host_fragment_content_principal
                                        )
                                            .navigate(R.id.reportFragment)

                                    }

                                }
                            }

                        )
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_principal)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}