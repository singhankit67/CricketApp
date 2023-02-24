package com.example.cricketapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.cricketapp.Data.CustomMatchDetailsClass
import com.example.cricketapp.Data.Player
import com.example.cricketapp.Data.PlayerCustom
import com.example.cricketapp.Util.UtilClass

class SinglePlayerAdapter (private val data: List<PlayerCustom>, private val context: Context): RecyclerView.Adapter<SinglePlayerAdapter.ViewHolder>() {
    val utilClass = UtilClass()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: TextView = itemView.findViewById(R.id.player_name)
        val isCaptain : TextView = itemView.findViewById(R.id.captain_text)
        val isKeeper : TextView = itemView.findViewById(R.id.wicket_keeper_text)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinglePlayerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_list_single_item, parent, false)
        return SinglePlayerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.playerName.text = data[position].playerName
        if(data[position].isCaptain){
            holder.isCaptain.visibility = View.VISIBLE
        }
        else{
            holder.isCaptain.visibility = View.GONE
        }
        if (data[position].isKeeper){
            holder.isKeeper.visibility = View.VISIBLE
        }
        else{
            holder.isKeeper.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            val dialogView = LayoutInflater.from(holder.itemView.context).inflate(R.layout.custom_dialog, null)
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setView(dialogView)
            val dialog = builder.create()
            dialogView.findViewById<TextView>(R.id.close_button).setOnClickListener { dialog.dismiss() }
            dialogView.findViewById<TextView>(R.id.player_name_on_dialog).text = data[position].playerName
            dialogView.findViewById<TextView>(R.id.batting_style_text).text = data[position].battingStyle
            dialogView.findViewById<TextView>(R.id.bowling_style_text).text = data[position].bowlingStyle
            dialog.show()

        }
    }
    override fun getItemCount(): Int {
        return  data.size
    }




}