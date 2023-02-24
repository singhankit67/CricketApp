package com.example.cricketapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cricketapp.Data.Player
import com.example.cricketapp.Data.PlayerCustom
import com.example.cricketapp.Network.RetrofitClient
import com.example.cricketapp.ViewModel.MatchViewModel
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamAFragment : Fragment() {
    var recyclerViewTeamA : RecyclerView? = null
    var playerList = ArrayList<PlayerCustom>()
    var dataa = "23"
    private lateinit var adapter : SinglePlayerAdapter
    private val viewModel by activityViewModels<MatchViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_teama_details, container, false)
        recyclerViewTeamA = view.findViewById(R.id.recycler_view_teama)
        viewModel.team1Name.observe(viewLifecycleOwner) { data ->
            Log.e("MyFragment", "Received data: $data")
            dataa = data
        }
        adapter = SinglePlayerAdapter(playerList,requireContext())
        return view
    }
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(isVisibleToUser){
            playerList.clear()
            Handler().postDelayed({ getDataOfTeamAPlayers()},500)

        }
    }

    fun getDataOfTeamAPlayers(){
        if(dataa == "NZ") {
            val call = RetrofitClient.apiService.fetchData()
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    val data = response.body().toString()
                    val jsonObj = JSONObject(data)
                    val teamsObj = jsonObj.getJSONObject("Teams")
//                val keys = teamsObj.keys()
//                var firstkey : String?= null
//                if(keys.hasNext()){
//                    firstkey = keys.next()
//                    val firstTeam = teamsObj.getJSONObject(firstkey!!)
//                    dataa = firstTeam.getString("Name_Short")
//                }
//
//                Log.e("team1short",firstkey.toString())
                    for (i in 0 until teamsObj.length()) {
                        val teamObj = teamsObj.getJSONObject(teamsObj.names().getString(i))
                        if (teamObj.getString("Name_Short") == dataa) { //Check if the team is India
                            val playersObj = teamObj.getJSONObject("Players")
                            for (j in 0 until playersObj.length()) { //Loop through each player
                                val playerObj =
                                    playersObj.getJSONObject(playersObj.names().getString(j))
                                val playerName = playerObj.getString("Name_Full")
                                val battingStyle =
                                    playerObj.getJSONObject("Batting").getString("Style")
                                val bowlingStyle =
                                    playerObj.getJSONObject("Bowling").getString("Style")
                                if (playerObj.has("Iscaptain")) {
                                    val singlePlayerEntryForCaptain = PlayerCustom(
                                        playerName,
                                        battingStyle,
                                        bowlingStyle,
                                        true,
                                        false
                                    )
                                    playerList.add(singlePlayerEntryForCaptain)
                                } else if (playerObj.has("Iskeeper")) {
                                    val singlePlayerEntryForKeeper = PlayerCustom(
                                        playerName,
                                        battingStyle,
                                        bowlingStyle,
                                        false,
                                        true
                                    )
                                    playerList.add(singlePlayerEntryForKeeper)
                                } else if (playerObj.has("Iscaptain") && playerObj.has("Iskeeper")) {
                                    val singlePlayerEntryForKeeperandPlayer = PlayerCustom(
                                        playerName,
                                        battingStyle,
                                        bowlingStyle,
                                        true,
                                        true
                                    )
                                    playerList.add(singlePlayerEntryForKeeperandPlayer)
                                } else {
                                    val singlePlayerEntry =
                                        PlayerCustom(playerName, battingStyle, bowlingStyle)
                                    playerList.add(singlePlayerEntry)
                                }
                            }
                        }
                    }

                    adapter = SinglePlayerAdapter(playerList, requireContext())
                    recyclerViewTeamA!!.layoutManager =
                        LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    recyclerViewTeamA!!.adapter = adapter
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.e(TAG, "Failed to fetch data: ${t.message}")
                }

            })
        }
        else if(dataa == "SA"){
            val call = RetrofitClient.apiService.fetchData1()
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    val data = response.body().toString()
                    val jsonObj = JSONObject(data)
                    val teamsObj = jsonObj.getJSONObject("Teams")
//                val keys = teamsObj.keys()
//                var firstkey : String?= null
//                if(keys.hasNext()){
//                    firstkey = keys.next()
//                    val firstTeam = teamsObj.getJSONObject(firstkey!!)
//                    dataa = firstTeam.getString("Name_Short")
//                }
//
//                Log.e("team1short",firstkey.toString())
                    for (i in 0 until teamsObj.length()) {
                        val teamObj = teamsObj.getJSONObject(teamsObj.names().getString(i))
                        if (teamObj.getString("Name_Short") == dataa) { //Check if the team is India
                            val playersObj = teamObj.getJSONObject("Players")
                            for (j in 0 until playersObj.length()) { //Loop through each player
                                val playerObj =
                                    playersObj.getJSONObject(playersObj.names().getString(j))
                                val playerName = playerObj.getString("Name_Full")
                                val battingStyle =
                                    playerObj.getJSONObject("Batting").getString("Style")
                                val bowlingStyle =
                                    playerObj.getJSONObject("Bowling").getString("Style")
                                if (playerObj.has("Iscaptain")) {
                                    val singlePlayerEntryForCaptain = PlayerCustom(
                                        playerName,
                                        battingStyle,
                                        bowlingStyle,
                                        true,
                                        false
                                    )
                                    playerList.add(singlePlayerEntryForCaptain)
                                } else if (playerObj.has("Iskeeper")) {
                                    val singlePlayerEntryForKeeper = PlayerCustom(
                                        playerName,
                                        battingStyle,
                                        bowlingStyle,
                                        false,
                                        true
                                    )
                                    playerList.add(singlePlayerEntryForKeeper)
                                } else if (playerObj.has("Iscaptain") && playerObj.has("Iskeeper")) {
                                    val singlePlayerEntryForKeeperandPlayer = PlayerCustom(
                                        playerName,
                                        battingStyle,
                                        bowlingStyle,
                                        true,
                                        true
                                    )
                                    playerList.add(singlePlayerEntryForKeeperandPlayer)
                                } else {
                                    val singlePlayerEntry =
                                        PlayerCustom(playerName, battingStyle, bowlingStyle)
                                    playerList.add(singlePlayerEntry)
                                }
                            }
                        }
                    }

                    adapter = SinglePlayerAdapter(playerList, requireContext())
                    recyclerViewTeamA!!.layoutManager =
                        LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    recyclerViewTeamA!!.adapter = adapter
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    Log.e(TAG, "Failed to fetch data: ${t.message}")
                }

            })
        }
        else{
            Log.e("no data","no data")
        }
    }


}
