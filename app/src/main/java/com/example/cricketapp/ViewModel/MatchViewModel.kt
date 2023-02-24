package com.example.cricketapp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MatchViewModel: ViewModel(){

   val team1Name = MutableLiveData<String>()
   val team2Name = MutableLiveData<String>()

//    fun loadMatchDetails() {
//        viewModelScope.launch {
//            try {
//                val result = api.getMatchDetails()
//                _matchDetails.value = result
//            } catch (e: Exception) {
//                //do ntng
//            }
//        }
//    }
}