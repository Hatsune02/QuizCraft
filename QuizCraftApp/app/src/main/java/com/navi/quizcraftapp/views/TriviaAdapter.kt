package com.navi.quizcraftapp.views

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.navi.quizcraftapp.R
import com.navi.quizcraftapp.model.Trivia

class TriviaAdapter(private val triviaList: List<Trivia>, private val itemClick: (Trivia) -> Unit) :
    RecyclerView.Adapter<TriviaAdapter.TriviaViewHolder>() {

    class TriviaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val triviaName: TextView = view.findViewById(R.id.triviaName)
        val triviaTheme: TextView = view.findViewById(R.id.triviaTopic)
        val triviaTime: TextView = view.findViewById(R.id.triviaTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TriviaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_trivia, parent, false)
        return TriviaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TriviaViewHolder, position: Int) {
        val trivia = triviaList[position]
        holder.triviaName.text = trivia.name
        holder.triviaTheme.text = "Tema: " + trivia.topic
        holder.triviaTime.text = "Tiempo: " + trivia.questionTime.toString() + " segundos"

        holder.itemView.setOnClickListener { itemClick(trivia) }
    }

    override fun getItemCount(): Int = triviaList.size
}