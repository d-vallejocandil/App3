package com.example.app3

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("games")
    fun get_games(): List<Game>
    @GET("game")
    fun get_game(@Query("id") id: Int): Game
    companion object {
        fun get(): Api {
            return Retrofit.Builder().baseUrl("https://www.freetogame.com/api/").addConverterFactory(GsonConverterFactory.create()).build().create(Api::class.java)
        }
    }
}