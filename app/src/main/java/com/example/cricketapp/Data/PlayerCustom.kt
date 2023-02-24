package com.example.cricketapp.Data

data class PlayerCustom(
    val playerName : String,
    val battingStyle : String,
    val bowlingStyle: String,
    val isCaptain: Boolean = false,
    val isKeeper: Boolean = false
)
