package com.navi.quizcraftapp.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.navi.quizcraftapp.R
import com.navi.quizcraftapp.model.CollectedData
import com.navi.quizcraftapp.model.Trivia
import com.navi.quizcraftapp.parser_lexer.Compile
import com.navi.quizcraftapp.socket.SocketManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class TriviaResultsActivity : AppCompatActivity() {
    private lateinit var trivia: Trivia
    private lateinit var userAnswers: HashMap<Int, Pair<List<String>, Long>>
    private var triviaTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia_results)
        setUI()
    }

    private fun setUI(){
        trivia = intent.getSerializableExtra("trivia") as Trivia
        userAnswers = intent.getSerializableExtra("userAnswers") as HashMap<Int, Pair<List<String>, Long>>

        triviaTime = 0
        val score = findViewById<TextView>(R.id.score)
        val userScore = calculateScore()
        score.text = "Puntaje: " + userScore + "/100"
        displayResults(trivia, userAnswers)
        val time = findViewById<TextView>(R.id.time)
        time.text = "Tiempo: "+triviaTime.toInt().toString()+" segundos"
        val btnBackToTrivia = findViewById<Button>(R.id.btnBackToTrivia)
        btnBackToTrivia.setOnClickListener { navigateTrivias() }

        sendData(userScore)
    }

    private fun sendData(userScore:Int ){
        val sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        var pass = false;

        if(userScore > 60) pass = true
        val data = CollectedData(username, trivia.idTrivia, triviaTime.toInt(), userScore, pass)

        val textData = "<?xson version=\"1.0\" ?>\n" +
                "<!realizar_solicitud: \"ADD_DATA\" > " + data.dbString() + "\n<fin_solicitud_realizada!>"

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val socketManager = SocketManager(this@TriviaResultsActivity, 5000)
                socketManager.connect()
                socketManager.sendData(textData)
                val response = socketManager.receiveData()
                println(response)

                socketManager.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun navigateTrivias(){
        val intent = Intent(this, TriviaActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun displayResults(trivia: Trivia, userAnswers: HashMap<Int, Pair<List<String>, Long>>) {
        val resultsContainer = findViewById<LinearLayout>(R.id.resultsContainer)

        for ((index, component) in trivia.components.withIndex()) {
            val correctAnswers = component.answer.map { normalizeText(it) }
            val userResponse = userAnswers[index]?.first?.map { normalizeText(it) } ?: listOf()
            val elapsedTime = userAnswers[index]?.second?.toInt()

            val resultView = TextView(this)
            resultView.text = "Pregunta ${index + 1}: ${component.visibleText}\nRespuesta del usuario: ${userResponse.joinToString()}\nTiempo para responder: ${elapsedTime}\n"

            println(correctAnswers.containsAll(userResponse))
            if (correctAnswers.containsAll(userResponse)) {
                resultView.setTextColor(ContextCompat.getColor(this, R.color.green))
            }
            else {
                resultView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
            }

            resultsContainer.addView(resultView)
        }
    }
    private fun calculateScore(): Int {
        var totalScore = 0.0
        val questionsCount = trivia.components.size
        val pointsPerQuestion = 100.0 / questionsCount

        for ((index, component) in trivia.components.withIndex()) {
            val correctAnswers = component.answer.map { normalizeText(it) }
            val userAnswerPair = userAnswers[index]

            if (userAnswerPair != null) {
                val userSelected = userAnswerPair.first.map { normalizeText(it) }
                val elapsedTime = userAnswerPair.second

                var questionScore = pointsPerQuestion

                // Verificar si las respuestas son correctas
                if (correctAnswers.size != userSelected.size || !correctAnswers.containsAll(userSelected)) {
                    questionScore = 0.0
                }

                // Si el tiempo utilizado es mayor que el permitido, aplicar penalización
                if (elapsedTime > trivia.questionTime) {
                    questionScore *= 0.5
                }
                triviaTime += elapsedTime

                totalScore += questionScore
            }
        }

        return totalScore.toInt()
    }

    private fun normalizeText(text: String): String {
        return text.trim()
            .replace(Regex("\\s+"), " ")
            .lowercase()
    }
}