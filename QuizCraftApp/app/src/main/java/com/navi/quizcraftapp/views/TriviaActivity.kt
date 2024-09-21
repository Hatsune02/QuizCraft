package com.navi.quizcraftapp.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.navi.quizcraftapp.R
import com.navi.quizcraftapp.model.CollectedData
import com.navi.quizcraftapp.model.Component
import com.navi.quizcraftapp.model.Trivia


class TriviaActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var triviaAdapter: TriviaAdapter
    private var triviaList = ArrayList<Trivia>() // Tu lista de trivias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val trivia = Trivia("trivia1", "nombre Trivia", "Topic1", 10, "yo", "2004-08-08")
        val trivia2 = Trivia("trivia2", "nombre Trivia2", "Topic2", 100, "yo", "2004-08-08")

        triviaList.add(trivia)
        triviaList.add(trivia2)

        triviaAdapter = TriviaAdapter(triviaList) { trivia ->
            // Manejar el clic en la trivia
            val intent = Intent(this, TriviaDetailActivity::class.java)
            intent.putExtra("triviaId", trivia.idTrivia) // O pasa el objeto completo si prefieres
            startActivity(intent)
        }

        recyclerView.adapter = triviaAdapter
    }
}