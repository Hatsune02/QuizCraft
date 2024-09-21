package com.navi.quizcraftapp.views

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.navi.quizcraftapp.R
import com.navi.quizcraftapp.socket.SocketManager
import kotlinx.coroutines.*
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val submitButton = findViewById<Button>(R.id.submit_button)
        val editText = findViewById<EditText>(R.id.request_field);
        submitButton.setOnClickListener { loginRequest(editText) }

    }

    private fun loginRequest(editText: EditText){
        val loginData = editText.text.toString();
        println(loginData)
        // Iniciar la comunicación en un hilo separado para no bloquear la UI
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val socketManager = SocketManager(this@LoginActivity, 5000)
                socketManager.connect()
                socketManager.sendData(loginData)

                val response = socketManager.receiveData()
                println(response)
                // Manejar la respuesta del servidor
                socketManager.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
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
        editText.hint = "IP Address"

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