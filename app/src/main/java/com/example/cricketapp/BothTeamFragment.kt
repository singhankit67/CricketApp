package com.example.cricketapp

import android.content.ContentValues
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cricketapp.Data.PlayerCustom
import com.example.cricketapp.Network.RetrofitClient
import com.example.cricketapp.ViewModel.MatchViewModel
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class BothTeamFragment : Fragment() {
    var recyclerViewBothTeams : RecyclerView? = null
    var bothTeamPlayerList = ArrayList<PlayerCustom>()
    private val viewModel by activityViewModels<MatchViewModel>()
    var team1shortname :String ?= null
    var team2shortname : String ?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.both_team_details, container, false)
        recyclerViewBothTeams = view.findViewById(R.id.recycler_view_teamboth)
        viewModel.team1Name.observe(viewLifecycleOwner) { data ->
            Log.e("MyFragment", "Received data: $data")
            team1shortname = data
        }
        viewModel.team2Name.observe(viewLifecycleOwner){ data ->
            Log.e("MyFragment", "Received data: $data")
            team2shortname = data
        }
        return view
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser){
            bothTeamPlayerList.clear()
            getDataForBothTeams()
        }
    }
    fun getDataForBothTeams(){
        if(team1shortname == "NZ" && team2shortname == "IND"){
            val call = RetrofitClient.apiService.fetchData()
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    val data = response.body().toString()
                    val jsonObj = JSONObject(data)
                    val teamsObj = jsonObj.getJSONObject("Teams")
                    for(i in 0 until teamsObj.length()) {
                        val teamObj = teamsObj.getJSONObject(teamsObj.names().getString(i))
                            val playersObj = teamObj.getJSONObject("Players")
                            for (j in 0 until playersObj.length()) { //Loop through each player
                                val playerObj = playersObj.getJSONObject(playersObj.names().getString(j))
                                val playerName = playerObj.getString("Name_Full")
                                val battingStyle = playerObj.getJSONObject("Batting").getString("Style")
                                val bowlingStyle = playerObj.getJSONObject("Bowling").getString("Style")
                                if (playerObj.has("Iscaptain")){
                                    val isCaptainvalue = playerObj.getBoolean("Iscaptain")
                                    if (isCaptainvalue) {
                                        val singlePlayerEntryForCaptain = PlayerCustom(playerName, battingStyle, bowlingStyle, true, false)
                                        bothTeamPlayerList.add(singlePlayerEntryForCaptain)
                                    }
                                }
                                else if(playerObj.has("Iskeeper")){
                                    val isKeepervalue = playerObj.getBoolean("Iskeeper")
                                    if(isKeepervalue) {
                                        val singlePlayerEntryForKeeper = PlayerCustom(playerName, battingStyle, bowlingStyle, false, true)
                                        bothTeamPlayerList.add(singlePlayerEntryForKeeper)
                                    }
                                }
                                else if(playerObj.has("Iscaptain") && playerObj.has("Iskeeper")){
                                    val isCaptainvalue = playerObj.getBoolean("Iscaptain")
                                    val isKeepervalue = playerObj.getBoolean("Iskeeper")
                                    if(isCaptainvalue && isKeepervalue) {
                                        val singlePlayerEntryForKeeperandPlayer = PlayerCustom(playerName, battingStyle, bowlingStyle, true, true)
                                        bothTeamPlayerList.add(singlePlayerEntryForKeeperandPlayer)
                                    }
                                }
                                else{
                                    val singlePlayerEntry = PlayerCustom(playerName, battingStyle, bowlingStyle)
                                    bothTeamPlayerList.add(singlePlayerEntry)
                                }
                            }

                    }

                    val adapter = SinglePlayerAdapter(bothTeamPlayerList, requireContext())
                    recyclerViewBothTeams!!.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    recyclerViewBothTeams!!.adapter = adapter
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.e(ContentValues.TAG, "Failed to fetch data: ${t.message}")
                }

            })
        }
        else if(team1shortname == "SA" && team2shortname == "PAK"){
            val call = RetrofitClient.apiService.fetchData1()
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    val data = response.body().toString()
                    val jsonObj = JSONObject(data)
                    val teamsObj = jsonObj.getJSONObject("Teams")
                    for(i in 0 until teamsObj.length()) {
                        val teamObj = teamsObj.getJSONObject(teamsObj.names().getString(i))
                        val playersObj = teamObj.getJSONObject("Players")
                        for (j in 0 until playersObj.length()) { //Loop through each player
                            val playerObj = playersObj.getJSONObject(playersObj.names().getString(j))
                            val playerName = playerObj.getString("Name_Full")
                            val battingStyle = playerObj.getJSONObject("Batting").getString("Style")
                            val bowlingStyle = playerObj.getJSONObject("Bowling").getString("Style")
                            if (playerObj.has("Iscaptain")){
                                val singlePlayerEntryForCaptain = PlayerCustom(playerName, battingStyle, bowlingStyle,true,false)
                                bothTeamPlayerList.add(singlePlayerEntryForCaptain)
                            }
                            else if(playerObj.has("Iskeeper")){
                                val singlePlayerEntryForKeeper = PlayerCustom(playerName, battingStyle, bowlingStyle,false,true)
                                bothTeamPlayerList.add(singlePlayerEntryForKeeper)
                            }
                            else if(playerObj.has("Iscaptain") && playerObj.has("Iskeeper")){
                                val singlePlayerEntryForKeeperandPlayer = PlayerCustom(playerName, battingStyle, bowlingStyle,true,true)
                                bothTeamPlayerList.add(singlePlayerEntryForKeeperandPlayer)
                            }
                            else{
                                val singlePlayerEntry = PlayerCustom(playerName, battingStyle, bowlingStyle)
                                bothTeamPlayerList.add(singlePlayerEntry)
                            }
                        }

                    }

                    val adapter = SinglePlayerAdapter(bothTeamPlayerList, requireContext())
                    recyclerViewBothTeams!!.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    recyclerViewBothTeams!!.adapter = adapter
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.e(ContentValues.TAG, "Failed to fetch data: ${t.message}")
                }

            })
        }
    }

}
