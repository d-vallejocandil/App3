package com.example.app3

import okhttp3.internal.platform.Platform

data class Game(
    val id: Int,
    val title: String,
    val platform: String,
    val release_date: String
)