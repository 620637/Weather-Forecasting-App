package com.example.weather.API

import com.google.gson.annotations.SerializedName

data class AirQuality(
    val co: String,
    @SerializedName("gb-defra-index") val gbDefraIndex: String,
    val no2: String,
    val o3: String,
    val pm10: String,
    @SerializedName("pm2_5") val pm2_5: String,
    val so2: String,
    @SerializedName("us-epa-index") val usEpaIndex: String
)
