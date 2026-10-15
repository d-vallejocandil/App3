package com.example.app3

import android.app.ActivityGroup
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app3.databinding.ActivityGameBinding
import com.example.app3.databinding.GameBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {
    val api = Api.get()
    lateinit var binding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val id = intent.getIntExtra("GAME", 540)
        supportActionBar?.title = id.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        getGame(id)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
    fun getGame(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val game = api.getGame(id)
            CoroutineScope(Dispatchers.Main).launch {
                Picasso.get().load(game.thumbnail).into(binding.thumbnail)
                binding.title.text = game.title
                binding.genre.text = game.genre
                binding.platform.text = game.platform
                binding.releaseDate.text = game.release_date
            }
        }
    }
}