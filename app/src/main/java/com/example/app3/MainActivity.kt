package com.example.app3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app3.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val api = Api.get()
    lateinit var binding: ActivityMainBinding
    var games: List<Game> = emptyList()
    var games2: List<Game> = emptyList()
    lateinit var gameAdapter: GameAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title = "Home"
        gameAdapter = GameAdapter(games) {
            val game = games[it]
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("GAME", game.id)
            startActivity(intent)
        }
        binding.recycler.adapter = gameAdapter
        binding.recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getGames()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null) {
                    games2 = games.filter {
                        it.title.contains(newText, ignoreCase = true)
                    }
                    update(games2)
                }
                return true
            }
        })
        return true
    }
    fun getGames() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = api.getGames()
            CoroutineScope(Dispatchers.Main).launch {
                games = list
                games2 = list
                update(games2)
            }
        }
    }
    fun update(games: List<Game>) {
        gameAdapter.update(games)
    }
}