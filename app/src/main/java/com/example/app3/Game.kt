package com.example.app3

import okhttp3.internal.platform.Platform

data class Game(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val game_url: String,
    val genre: String,
    val platform: String,
    val release_date: String,
    val description: String?,
    val minimum_system_requirements: MinimumSystemRequirements?,
    val screenshots: List<Screenshot>?
)