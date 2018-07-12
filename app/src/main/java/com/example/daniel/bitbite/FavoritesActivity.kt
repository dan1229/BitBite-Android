package com.example.daniel.bitbite

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.util.Log
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class FavoritesActivity : AppCompatActivity() {

    /** Variables **/
    lateinit var user : MainActivity.User
    private lateinit var mDrawerLayout: DrawerLayout

    /** ON CREATE **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        // Get intent extras
        user = intent.getParcelableExtra("USER")

        // Set Nav Drawer listener
        nav_view.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            mDrawerLayout.closeDrawers()
            navMenuSwitch(menuItem)
            true
        }

        // Set Nav Footer listener
        nav_footer.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = false
            mDrawerLayout.closeDrawers()
            navMenuSwitch(menuItem)
            false
        }
    }

    /**====================================================================================================**/
    /** Option Menu/Settings **/

    // navMenuSwitch()
    // Calls appropriate function(s) based on Nav Drawer input
    private
    fun navMenuSwitch(menuItem: MenuItem) {
        when(menuItem.toString()) {
            "Home" -> goHome()
            "Favorites" -> mDrawerLayout.closeDrawers()
            "Settings" -> goToSettings()
            "About Us" -> goToAboutUs(this)
            "Feedback" -> goToFeedback(this)
        }
    }

    /**====================================================================================================**/
    /** Intent Makers **/

    // goHome()
    // Go to MainActivity.kt
    private
    fun goHome() {
        val intent = Intent(this@FavoritesActivity, MainActivity::class.java)
        startActivity(intent)
    }

    // goToSettings()
    // Go to SettingsActivity.kt
    private
    fun goToSettings() {
        val intent = Intent(this@FavoritesActivity, SettingsActivity::class.java)
        startActivity(intent)
    }
}
