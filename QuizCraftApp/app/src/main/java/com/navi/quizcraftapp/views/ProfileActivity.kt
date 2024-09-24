package com.navi.quizcraftapp.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.navi.quizcraftapp.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setUI()
    }
    private fun setUI(){
        viewUser()
        btn()
    }
    private fun viewUser(){
        val sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)

        val tvUsername:TextView = findViewById(R.id.tvUsername)
        val tvPassword:TextView = findViewById(R.id.tvPassword)
        val tvInstitution:TextView = findViewById(R.id.tvInstitution)
        val tvFullName:TextView = findViewById(R.id.tvFullName)
        val tvCreationDate:TextView = findViewById(R.id.tvCreationDate)
        val tvUpdateDate:TextView = findViewById(R.id.tvUpdateDate)


        val username = sharedPreferences.getString("username", "")
        val password = sharedPreferences.getString("password", "")
        val institution = sharedPreferences.getString("institution", "")
        val name = sharedPreferences.getString("name", "")
        val creationDate = sharedPreferences.getString("creationDate", "")
        val updateDate = sharedPreferences.getString("updateDate", "")

        tvUsername.text = username
        tvPassword.text = password
        tvInstitution.text = institution
        tvFullName.text = name
        tvCreationDate.text = creationDate
        tvUpdateDate.text = updateDate
    }
    private fun btn(){
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener { logout() }
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

}