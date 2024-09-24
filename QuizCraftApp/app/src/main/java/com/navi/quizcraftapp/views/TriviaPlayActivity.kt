package com.navi.quizcraftapp.views

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.navi.quizcraftapp.R
import com.navi.quizcraftapp.model.Component
import com.navi.quizcraftapp.model.Trivia

class TriviaPlayActivity : AppCompatActivity() {
    private lateinit var trivia: Trivia
    private var currentIndex = 0
    private var userAnswers: HashMap<Int, Pair<List<String>, Long>> = HashMap()
    private var startTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia_play)

        setUI()
    }
    private fun setUI(){
        trivia = intent.getSerializableExtra("trivia") as Trivia

        showComponent(currentIndex)

        val btnNext = findViewById<Button>(R.id.btnNext)
        btnNext.setOnClickListener {
            saveUserResponse()  // Guarda la respuesta del usuario
            // Si es el último componente, cambia el botón a "Terminar"
            if (currentIndex == trivia.components.size - 1) {
                showResult()  // Muestra los resultados finales
            } else {
                currentIndex++
                showComponent(currentIndex)  // Muestra el siguiente componente
            }
        }
    }
    private fun showComponent(index: Int){
        val component = trivia.components[index]
        val container = findViewById<LinearLayout>(R.id.componentContainer)
        container.removeAllViews()

        when(component.clase){
            Component.CAMPO_TEXTO -> showComponentText(component, container)
            Component.AREA_TEXTO -> showComponentTextArea(component, container)
            Component.CHECKBOX -> showComponentCheckBox(component, container)
            Component.RADIO -> showComponentRadio(component, container)
            Component.FICHERO -> {}
            Component.COMBO -> showComponentCombo(component, container)
        }

        val btnNext = findViewById<Button>(R.id.btnNext)
        if(index == trivia.components.size - 1){
            btnNext.text = "Finalizar"
        }
        else btnNext.text = "Siguiente"
    }

    private fun showComponentText(component: Component, container: LinearLayout){
        startTime = System.currentTimeMillis()
        val view = layoutInflater.inflate(R.layout.component_text, container, false)
        val visibleText = view.findViewById<TextView>(R.id.tvVisibleText)
        visibleText.text = component.visibleText
        container.addView(view)
    }
    private fun showComponentTextArea(component: Component, container: LinearLayout){
        startTime = System.currentTimeMillis()
        val view = layoutInflater.inflate(R.layout.component_text_area, container, false)
        val visibleText = view.findViewById<TextView>(R.id.tvVisibleText)
        visibleText.text = component.visibleText

        val editTextArea = view.findViewById<EditText>(R.id.etAnswerArea)
        editTextArea.setLines(component.line)
        val columnWidthDp = 10 * (component.columns+1)
        editTextArea.layoutParams.width = (columnWidthDp * resources.displayMetrics.density).toInt()

        container.addView(view)
    }
    private fun showComponentCheckBox(component: Component, container: LinearLayout){
        startTime = System.currentTimeMillis()
        val view = layoutInflater.inflate(R.layout.component_checkbox, container, false)
        val visibleText = view.findViewById<TextView>(R.id.tvVisibleText)
        visibleText.text = component.visibleText

        val checkboxContainer = view.findViewById<LinearLayout>(R.id.checkboxContainer)

        component.options.forEach{ option ->
            val checkBox = CheckBox(this)
            checkBox.text = option
            checkboxContainer.addView(checkBox)
        }
        container.addView(view)
    }
    private fun showComponentRadio(component: Component, container: LinearLayout){
        startTime = System.currentTimeMillis()
        val view = layoutInflater.inflate(R.layout.component_radio, container, false)
        val visibleText = view.findViewById<TextView>(R.id.tvVisibleText)
        visibleText.text = component.visibleText

        val radioGroupContainer = view.findViewById<RadioGroup>(R.id.radioGroupComponent)

        component.options.forEach{ option ->
            val radioButton = RadioButton(this).apply {
                text = option
            }
            radioGroupContainer.addView(radioButton)
        }
        container.addView(view)
    }
    private fun showComponentCombo(component: Component, container: LinearLayout){
        startTime = System.currentTimeMillis()
        val view = layoutInflater.inflate(R.layout.component_combo, container, false)
        val visibleText = view.findViewById<TextView>(R.id.tvVisibleText)
        visibleText.text = component.visibleText

        val spinnerComponent = view.findViewById<Spinner>(R.id.spinnerComponent)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, component.options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerComponent.adapter = adapter

        container.addView(view)
    }

    private fun saveUserResponse(){
        val component = trivia.components[currentIndex]

        when(component.clase){
            Component.CAMPO_TEXTO -> saveTextAnswer()
            Component.AREA_TEXTO -> saveTextAreaAnswer()
            Component.CHECKBOX -> saveCheckBoxAnswers()
            Component.RADIO -> saveRadioButtonAnswer()
            Component.FICHERO -> {}
            Component.COMBO -> saveComboAnswer()
        }
    }

    private fun saveTextAnswer(){
        val elapsedTime = (System.currentTimeMillis() - startTime) / 1000
        val editText = findViewById<EditText>(R.id.etAnswer)
        val userInput = editText.text.toString().trim()
        userAnswers[currentIndex] = Pair(listOf(userInput), elapsedTime)

    }
    private fun saveTextAreaAnswer(){
        val elapsedTime = (System.currentTimeMillis() - startTime) / 1000
        val editText = findViewById<EditText>(R.id.etAnswerArea)
        val userInput = editText.text.toString().trim()
        userAnswers[currentIndex] = Pair(listOf(userInput), elapsedTime)
    }
    private fun saveCheckBoxAnswers(){
        val elapsedTime = (System.currentTimeMillis() - startTime) / 1000
        val selectedOptions = mutableListOf<String>()
        val checkboxContainer = findViewById<LinearLayout>(R.id.checkboxContainer)

        // Iterar sobre todos los hijos (los CheckBoxes) del contenedor
        for (i in 0 until checkboxContainer.childCount) {
            val checkBox = checkboxContainer.getChildAt(i) as CheckBox
            if (checkBox.isChecked) {
                selectedOptions.add(checkBox.text.toString())
            }
        }

        // Guardar las respuestas seleccionadas en el HashMap
        userAnswers[currentIndex] = Pair(selectedOptions, elapsedTime)
    }
    private fun saveRadioButtonAnswer() {
        val elapsedTime = (System.currentTimeMillis() - startTime) / 1000
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupComponent)
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId

        if (selectedRadioButtonId != -1) { // Verificar si hay una selección
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
            val selectedOption = selectedRadioButton.text.toString()

            // Guardar la respuesta en el HashMap
            userAnswers[currentIndex] = Pair(listOf(selectedOption), elapsedTime)
        }
        else userAnswers[currentIndex] = Pair(listOf(), elapsedTime)
    }
    private fun saveComboAnswer() {
        val elapsedTime = (System.currentTimeMillis() - startTime) / 1000
        val spinnerComponent = findViewById<Spinner>(R.id.spinnerComponent)
        val selectedOption = spinnerComponent.selectedItem.toString()

        userAnswers[currentIndex] = Pair(listOf(selectedOption), elapsedTime)
    }


    private fun showResult() {
        val score = calculateScore()
        val intent = Intent(this, TriviaResultsActivity::class.java)
        intent.putExtra("userAnswers", userAnswers)
        intent.putExtra("trivia", trivia)
        startActivity(intent)
        finish()
    }
    private fun calculateScore(): Int {
        var score = 0

        /*// Iterar sobre todos los componentes
        for ((index, component) in trivia.components.withIndex()) {
            val correctAnswers = component.answer.map { normalizeText(it) } // Respuestas correctas normalizadas
            val userSelected = userAnswers[index]?.map { normalizeText(it) } ?: listOf() // Respuestas del usuario normalizadas

            // Verificar si las respuestas coinciden (ignorar mayúsculas y espacios extra)
            if (correctAnswers.size == userSelected.size && correctAnswers.containsAll(userSelected)) {
                score++  // Incrementar el puntaje si es correcto
            }
        }*/
        return score
    }
    private fun normalizeText(text: String): String {
        return text.trim()
            .replace(Regex("\\s+"), " ")
            .lowercase()
    }
}