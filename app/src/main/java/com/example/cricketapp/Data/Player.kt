package com.example.cricketapp.Data

data class Player(
    val Position : Int,
    val Name_Full : String,
    val Iskeeper : Boolean = false,
    val IsCaptain: Boolean = false,
    val Batting : Batting,
    val Bowling : Bowling
)
