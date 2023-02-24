package com.example.cricketapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cricketapp.Data.CustomMatchDetailsClass
import com.example.cricketapp.Util.UtilClass
import de.hdodenhof.circleimageview.CircleImageView

class MyAdapter(private val data: List<CustomMatchDetailsClass>,private val context:Context):RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    val utilClass = UtilClass()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tournamentName : TextView = itemView.findViewById(R.id.tournament_name)
        val team1Name: TextView = itemView.findViewById(R.id.team1_short_text)
        val team2Name : TextView = itemView.findViewById(R.id.team2_short_text)
        val seriesStatus : TextView = itemView.findViewById(R.id.status_of_the_series)
        val dayOfTheMatch : TextView = itemView.findViewById(R.id.day_and_time_of_the_match_starting)
        val locationOfTheMatch : TextView = itemView.findViewById(R.id.location_of_match)
        val teamInfoButton : TextView = itemView.findViewById(R.id.team_info_button)
        val team1Flag : CircleImageView = itemView.findViewById(R.id.team1_logo)
        val team2Flag : CircleImageView = itemView.findViewById(R.id.team2_logo)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.matchdetails_single_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tournamentName.text = data[position].tournamentName
        holder.team1Name.text = utilClass.countryName(data[position].team1Name)
        holder.team2Name.text = utilClass.countryName(data[position].team2Name)
        holder.seriesStatus.text = data[position].status
        holder.dayOfTheMatch.text = data[position].matchDate + "," + data[position].matchTime
        holder.locationOfTheMatch.text = data[position].venue
        holder.teamInfoButton.setOnClickListener {
            val intent = Intent(context,SquadDetailsActivity::class.java)
            intent.putExtra("team1Name",utilClass.countryName(data[position].team1Name))
            intent.putExtra("team2Name",utilClass.countryName(data[position].team2Name))
            context.startActivity(intent)

        }
        if(utilClass.countryName(data[position].team1Name) == "IND"){
            holder.team1Flag.setBackgroundResource(R.drawable.indiaroundd)
        }
        else if(utilClass.countryName(data[position].team1Name) == "PAK"){
            holder.team1Flag.setBackgroundResource(R.drawable.pakround)
        }
        else  if(utilClass.countryName(data[position].team1Name) == "NZ"){
            holder.team1Flag.setBackgroundResource(R.drawable.nzro)
        }
        else  if(utilClass.countryName(data[position].team1Name) == "SA"){
            holder.team1Flag.setBackgroundResource(R.drawable.saroundflagg)
        }

        if(utilClass.countryName(data[position].team2Name) == "IND"){
            holder.team2Flag.setBackgroundResource(R.drawable.indiaroundd)
        }
        else if(utilClass.countryName(data[position].team2Name) == "PAK"){
            holder.team2Flag.setBackgroundResource(R.drawable.pakround)
        }
        else  if(utilClass.countryName(data[position].team2Name) == "NZ"){
            holder.team2Flag.setBackgroundResource(R.drawable.nzro)
        }
        else  if(utilClass.countryName(data[position].team2Name) == "SA"){
            holder.team2Flag.setBackgroundResource(R.drawable.saroundflagg)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
