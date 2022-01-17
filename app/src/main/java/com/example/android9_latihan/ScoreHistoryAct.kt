package com.example.android9_latihan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScoreHistoryAct : AppCompatActivity() {
    private lateinit var _rvScoreHistory: RecyclerView
    private var arScoreHistory = arrayListOf<scorehistory>()
//    val _backButton = findViewById<Button>(R.id.backButton)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_history)

        _rvScoreHistory = findViewById(R.id.rvScoreHistory)
        arScoreHistory.addAll(databaseHistory.database)
        display()
    }

    private fun display() {
        _rvScoreHistory.layoutManager = LinearLayoutManager(this)
        val adapter = adapterScoreHistory(arScoreHistory)
        _rvScoreHistory.adapter = adapter
    }
}