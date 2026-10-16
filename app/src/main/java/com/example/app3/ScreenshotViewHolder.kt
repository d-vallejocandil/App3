package com.example.app3

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app3.databinding.GameBinding
import com.example.app3.databinding.ScreenshotBinding
import com.squareup.picasso.Picasso

class ScreenshotViewHolder(val binding: ScreenshotBinding): RecyclerView.ViewHolder(binding.root) {
    fun render(screenshot: Screenshot) {
        Picasso.get().load(screenshot.image).into(binding.screenshot)
    }
}