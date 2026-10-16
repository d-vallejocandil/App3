package com.example.app3

import okhttp3.internal.platform.Platform

data class MinimumSystemRequirements(
    val os: String,
    val processor: String,
    val memory: String,
    val graphics: String,
    val storage: String
)