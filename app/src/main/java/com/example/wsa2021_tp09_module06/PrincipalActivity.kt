package com.example.wsa2021_tp09_module06

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import com.example.wsa2021_tp09_module06.databinding.ActivityPrincipalBinding
import com.example.wsa2021_tp09_module06.databinding.NavHeaderPrincipalBinding
import com.example.wsa2021_tp09_module06.helper.AlertJD
import com.example.wsa2021_tp09_module06.helper.Cast
import com.example.wsa2021_tp09_module06.helper.Singleton

class PrincipalActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityPrincipalBinding
    override fun onBackPressed() {
        Toast.makeText(this, "not permitted to go back", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarPrincipal.toolbar)

        binding.appBarPrincipal.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout

        val navView: NavigationView = binding.navView
        var headerJD = NavHeaderPrincipalBinding.bind(navView.getHeaderView(0))
        headerJD.txtName.text = Singleton.UserLogin.nome
        headerJD.textView.text = Singleton.UserLogin.email
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
                R.id.homeFragment, R.id.reportFragment, R.id.visualizarFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemInfo -> {
                this.AlertJD("V1.0.0 Desenvolvido por Juan Diego <DR> WSA 2021.") {

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