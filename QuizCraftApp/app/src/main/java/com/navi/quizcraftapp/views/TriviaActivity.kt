package com.navi.quizcraftapp.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.navi.quizcraftapp.R
import com.navi.quizcraftapp.model.CollectedData
import com.navi.quizcraftapp.model.Component
import com.navi.quizcraftapp.model.TestTrivias
import com.navi.quizcraftapp.model.Trivia
import com.navi.quizcraftapp.parser_lexer.Compile
import com.navi.quizcraftapp.socket.SocketManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException


class TriviaActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var triviaAdapter: TriviaAdapter
    private var triviaList = ArrayList<Trivia>() // Tu lista de trivias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        TestTrivias.initializateTrivias()
        triviaList = TestTrivias.trivias
        //triviaList = obtainTrivias()

        triviaAdapter = TriviaAdapter(triviaList) { trivia ->
            // Manejar el clic en la trivia
            val intent = Intent(this, TriviaDetailActivity::class.java)
            intent.putExtra("triviaObj", trivia) // O pasa el objeto completo si prefieres
            startActivity(intent)
        }

        recyclerView.adapter = triviaAdapter
    }
    private fun obtainTrivias(): ArrayList<Trivia> {
        val text = "";
        var trivias = ArrayList<Trivia>()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val socketManager = SocketManager(this@TriviaActivity, 5000)
                socketManager.connect()
                socketManager.sendData(text)

                val response = socketManager.receiveData()
                println(response)
                trivias = Compile.getTrivias(response)

                socketManager.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return trivias
    }
}