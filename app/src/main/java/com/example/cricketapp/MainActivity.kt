package com.example.cricketapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cricketapp.Data.CustomMatchDetailsClass
import com.example.cricketapp.Network.RetrofitClient
import com.example.cricketapp.ViewModel.MatchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    var teamMatchDetails = ArrayList<CustomMatchDetailsClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view_for_matches)
        CoroutineScope(Dispatchers.IO).launch {
            val matchDets = RetrofitClient.apiService.getMatchDetails()
            val matchDets1 = RetrofitClient.apiService.getMatchDetails1()
            withContext(Dispatchers.Main) {
                        val data =  CustomMatchDetailsClass(matchDets.Matchdetail.Team_Home,matchDets.Matchdetail.Team_Away,matchDets.Matchdetail.Match.Date,
                        matchDets.Matchdetail.Match.Time,matchDets.Matchdetail.Venue.Name,matchDets.Matchdetail.Series.Status,matchDets.Matchdetail.Series.Tour_Name)
                teamMatchDetails.add(data)
                val data1 = CustomMatchDetailsClass(matchDets1.Matchdetail.Team_Home,matchDets1.Matchdetail.Team_Away,matchDets1.Matchdetail.Match.Date,
                    matchDets1.Matchdetail.Match.Time,matchDets1.Matchdetail.Venue.Name,matchDets1.Matchdetail.Series.Status,matchDets1.Matchdetail.Series.Tour_Name)
                teamMatchDetails.add(data1)
                val adapter = MyAdapter(teamMatchDetails,this@MainActivity)
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = adapter
            }
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}