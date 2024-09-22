package com.navi.quizcraftapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.navi.quizcraftapp.views.LoginActivity
import com.navi.quizcraftapp.views.ProfileActivity
import com.navi.quizcraftapp.views.TriviaActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
        checkUser()
    }

    private fun initUI(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun checkUser(){
        val sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            navigateLogin()
        } else {
            // Si está logueado, continuar con la pantalla principal
            setContentView(R.layout.activity_main)

            val btnProfile = findViewById<CardView>(R.id.btnProfile)
            val btnTrivias = findViewById<CardView>(R.id.btnTrivias)
            val btnLogout = findViewById<Button>(R.id.btnLogout)

            btnProfile.setOnClickListener { navigateProfile() }
            btnTrivias.setOnClickListener { navigateTrivias() }
            btnLogout.setOnClickListener { logout() }
            // Obtener información del usuario
            /*val username = sharedPreferences.getString("username", "Usuario desconocido")
            val email = sharedPreferences.getString("email", "Sin correo electrónico")

            */

        }
    }
    private fun logout(){
        val sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        navigateLogin()
    }
    private fun navigateLogin(){
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        finish ()
    }

    private fun navigateProfile(){
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }
    private fun navigateTrivias(){
        val intent = Intent(this, TriviaActivity::class.java)
        startActivity(intent)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_set_ip -> {
                showSetIpDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSetIpDialog() {
        val editText = EditText(this)
        editText.hint = "Enter IP Address"

        // Mostrar un AlertDialog para que el usuario ingrese la IP
        AlertDialog.Builder(this)
            .setTitle("Ingresa direccion IP")
            .setView(editText)
            .setPositiveButton("Guardar") { dialog, _ ->
                val ip = editText.text.toString()

                // Guardar la IP en SharedPreferences
                val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                with(sharedPreferences.edit()) {
                    putString("ip_address", ip)
                    apply()
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}