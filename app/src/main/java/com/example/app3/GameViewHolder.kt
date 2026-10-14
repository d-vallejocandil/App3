package com.example.app3

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app3.databinding.GameBinding

class GameViewHolder(val binding: GameBinding): RecyclerView.ViewHolder(binding.root) {
    fun render(game: Game) {
        binding.title.text = game.title
    }
}