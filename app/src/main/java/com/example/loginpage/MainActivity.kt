package com.example.loginpage

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocate()
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.choose_language -> {
                showChangeLanguage()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showChangeLanguage() {
        val languageList = arrayOf("English", "عربي", "हिन्दी", "日本語", "മലയാളം", "தமிழ்", "తెలుగు")

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle(resources.getString(  R.string.select_language))
        builder.setSingleChoiceItems(languageList, -1){ dialog, which ->
            when (which) {
                0 -> {
                    setLocale("en")
                    recreate()
                }
                1 -> {
                    setLocale("ar")
                    recreate()
                }
                2 -> {
                    setLocale("hi")
                    recreate()
                }
                3 -> {
                    setLocale("ja")
                    recreate()
                }
                4 -> {
                    setLocale("ml")
                    recreate()
                }
                5 -> {
                    setLocale("ta")
                    recreate()
                }
                6 -> {
                    setLocale("te")
                    recreate()
                }
            }
            dialog.dismiss()
        }

        //resources.getResourceName(R.string.cancel)
        builder.setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
            dialog.dismiss()
        }

        val mDialog = builder.create()
        mDialog.show()
    }

    private fun setLocale(Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)

        val configuration = baseContext.resources.configuration
        configuration.locale = locale
        baseContext.resources.updateConfiguration(configuration, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun loadLocate(){
        val sharedPreference = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreference.getString("My_Lang", "")
        setLocale(language!!)
    }
}