package com.example.android9_latihan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class adapterScoreHistory (
    private val listScoreHistory: ArrayList<scorehistory>
): RecyclerView.Adapter<adapterScoreHistory.ListViewHolder>() {

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var _name: TextView = itemView.findViewById(R.id.tvPlayerName)
        var _score: TextView = itemView.findViewById(R.id.tvPlayerScore)
        var _time: TextView = itemView.findViewById(R.id.tvPlayTime)
        var _delete: ImageView = itemView.findViewById(R.id.imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.itemscorehistory, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listScoreHistory.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var data = listScoreHistory[position]

        holder._name.setText(data.name)
        holder._score.setText(data.score.toString())
        holder._time.setText(data.time)
        holder._delete.setOnClickListener{
            Toast.makeText(holder.itemView.context, "${data.name} deleted", Toast.LENGTH_LONG).show()
            listScoreHistory.remove(data)
            this.notifyItemRemoved(position)
        }
    }
}