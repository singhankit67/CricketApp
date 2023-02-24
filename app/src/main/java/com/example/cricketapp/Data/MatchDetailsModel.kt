package com.example.cricketapp.Data

data class MatchDetailsModel(
    val Innings: List<Inning>,
    val Matchdetail: Matchdetail,
    val Notes: Notes,
    val Nuggets: List<String>,
    val Teams: Teams
)