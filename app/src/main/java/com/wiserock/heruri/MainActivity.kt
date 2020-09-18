package com.wiserock.heruri

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.iid.FirebaseInstanceId
import com.wiserock.heruri.navigation.course.LectureFragment
import com.wiserock.heruri.navigation.course.LectureViewModel
import com.wiserock.heruri.navigation.home.HomeFragment
import com.wiserock.heruri.navigation.notifications.PushFragment
import com.wiserock.heruri.utils.AppPreferenceManager
import com.wiserock.heruri.utils.MyApp
import com.wiserock.template.model.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var dialog: ProgressBar
        lateinit var appDatabase: AppDatabase
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            if (!it.isSuccessful)
                println("it.exception = ${it.exception}")
            println("it.result?.id = ${it.result?.id}")
            println("it.result.token = ${it.result?.token}")
        }
        appDatabase = AppDatabase.getInstance(this)
        val preference = AppPreferenceManager
        val name = preference.getString(this, "name")
        val view: FrameLayout = findViewById(R.id.activity_main_fragment)
        Snackbar.make(view, "${name}님 안녕하세요!", Snackbar.LENGTH_SHORT).show()
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
                preference.setString(applicationContext, "username", "")
                preference.setString(applicationContext, "password", "")
                val viewModel =
                    ViewModelProvider(this).get(LectureViewModel::class.java)
                MyApp.init(viewModel)
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
                        .replace(R.id.activity_main_fragment, HomeFragment()).commitNow()
                    true
                }
                R.id.navigation_lecture -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main_fragment, LectureFragment()).commitNow()
                    true
                }
                R.id.navigation_notifications -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_main_fragment, PushFragment()).commitNow()
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