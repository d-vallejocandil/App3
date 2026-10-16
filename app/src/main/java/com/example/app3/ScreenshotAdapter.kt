package com.example.app3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app3.databinding.GameBinding
import com.example.app3.databinding.ScreenshotBinding

class ScreenshotAdapter(var screenshots: List<Screenshot>): RecyclerView.Adapter<ScreenshotViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenshotViewHolder {
        val binding = ScreenshotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScreenshotViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ScreenshotViewHolder, position: Int) {
        val screenshot = screenshots[position]
        holder.render(screenshot)
    }
    override fun getItemCount(): Int {
        return screenshots.size
    }
    fun update(screenshots: List<Screenshot>) {
        this.screenshots = screenshots
        notifyDataSetChanged()
    }
}