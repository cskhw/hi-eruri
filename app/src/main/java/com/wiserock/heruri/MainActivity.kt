package com.wiserock.heruri

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.wiserock.heruri.navigation.course.CourseFragment
import com.wiserock.heruri.navigation.home.HomeFragment
import com.wiserock.heruri.navigation.notifications.PushFragment
import com.wiserock.heruri.utils.AppPreferenceManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var dialog: ProgressBar
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val preference = AppPreferenceManager
        val name = preference.getString(this, "name")
        val view: BottomNavigationView = findViewById(R.id.activity_main_bottomNavigation)
        Snackbar.make(view, "${name}님 안녕하세요!", Snackbar.LENGTH_LONG).show()
        println("나의 홈그라운드에 온걸 환영한다 우후후...")
        activity_main_bottomNavigation.setOnNavigationItemSelectedListener(
            onNavigationItemSelectedListener()
        )
        activity_main_bottomNavigation.selectedItemId = R.id.navigation_home
        dialog = activity_main_progressBar
        dialog.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_menu_setting -> {
                startActivity(Intent(this, SettingActivity::class.java))
                true
            }
            R.id.app_menu_signOut -> {
                val preference = AppPreferenceManager
                preference.setString(this, "username", "")
                preference.setString(this, "password", "")
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            R.id.app_menu_coin -> {
                startActivity(Intent(this, CoinActivity::class.java))
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun onNavigationItemSelectedListener(): BottomNavigationView.OnNavigationItemSelectedListener? {
        return BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, HomeFragment()).commitNow()
                    true
                }
                R.id.navigation_course -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, CourseFragment()).commitNow()
                    true
                }
                R.id.navigation_notifications -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, PushFragment()).commitNow()
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}