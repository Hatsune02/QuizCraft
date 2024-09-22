package com.navi.quizcraftapp.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.navi.quizcraftapp.R
import com.navi.quizcraftapp.model.Trivia

class TriviaDetailActivity : AppCompatActivity() {
    private var comps: Int = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia_detail)

        showTrivia()
        buttonDo()
    }

    private fun showTrivia(){
        val trivia = intent.getSerializableExtra("triviaObj") as Trivia
        trivia.let{
            val idTrivia = it.idTrivia
            val name = it.name
            val topic = it.topic
            val questionTime = it.questionTime
            val createUser = it.createUser
            val creationDate = it.date

            val components = it.components
            val collectedData = it.collectedData

            val triviaId: TextView = findViewById(R.id.IdTrivia)
            val triviaName:TextView = findViewById(R.id.triviaDName)
            val triviaTopic:TextView = findViewById(R.id.triviaDTopic)
            val triviaQuestionTime:TextView = findViewById(R.id.triviaDQuestionTime)
            val triviaCreationUser:TextView = findViewById(R.id.triviaDCreateUser)
            val triviaCreationDate:TextView = findViewById(R.id.triviaDCreationDate)
            val triviaComponents:TextView = findViewById(R.id.triviaDComponents)
            val triviaAttempts:TextView = findViewById(R.id.triviaDAttempts)

            triviaName.text = name
            triviaId.text = idTrivia
            triviaTopic.text = topic
            triviaQuestionTime.text = questionTime.toString() + " segundos"
            triviaCreationUser.text = createUser
            triviaCreationDate.text = creationDate
            triviaComponents.text = components.size.toString()
            triviaAttempts.text = collectedData.size.toString() + " veces"

            comps = components.size
        }
    }
    private fun buttonDo(){
        if(comps > 0){
            val btnStart = findViewById<Button>(R.id.btnPlay)
            btnStart.setOnClickListener { startTrivia() }
        }
    }
    private fun startTrivia(){
        val trivia = intent.getSerializableExtra("triviaObj") as? Trivia
        val intent = Intent(this, TriviaPlayActivity::class.java)
        intent.putExtra("trivia", trivia)
        startActivity(intent)
    }
}