package com.example.cricketapp.Data


//to store only the needed info for showing on 1st screen
data class CustomMatchDetailsClass(
    val team1Name : String,
    val team2Name : String,
    val matchDate : String,
    val matchTime: String,
    val venue : String,
    val status :String,
    val tournamentName : String
)
