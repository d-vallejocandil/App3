package com.example.app3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app3.databinding.GameBinding

class GameAdapter(var games: List<Game>, val listener: (Int) -> Unit): RecyclerView.Adapter<GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = GameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.render(game)
        holder.itemView.setOnClickListener {
            listener(position)
        }
    }
    override fun getItemCount(): Int {
        return games.size
    }
    fun update(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
    }
}