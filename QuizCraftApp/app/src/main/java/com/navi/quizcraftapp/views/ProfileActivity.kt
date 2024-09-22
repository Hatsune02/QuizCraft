package com.navi.quizcraftapp.views

import android.content.Context
import android.os.Bundle
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
    }
    private fun viewUser(){
        val sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)

        val tvUsername:TextView = findViewById(R.id.tvUsername)
        val tvPassword:TextView = findViewById(R.id.tvPassword)
        val tvInstitution:TextView = findViewById(R.id.tvInstitution)
        val tvFullName:TextView = findViewById(R.id.tvFullName)
        val tvCreationDate:TextView = findViewById(R.id.tvCreationDate)


    }
}