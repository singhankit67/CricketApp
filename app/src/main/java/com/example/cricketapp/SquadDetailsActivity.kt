package com.example.cricketapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.cricketapp.ViewModel.MatchViewModel
import com.google.android.material.tabs.TabLayout

class SquadDetailsActivity : AppCompatActivity() {
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    private var backButtonForRideHistory : AppCompatImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_squad_details)
        backButtonForRideHistory = findViewById(R.id.goBackButton_squad)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        val intent = intent
        var team1Name = intent.getStringExtra("team1Name").toString()
        var team2Name = intent.getStringExtra("team2Name").toString()
        var team1String = team1Name.toString()
        var team2String = team2Name.toString()
        backButtonForRideHistory!!.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        tabLayout!!.addTab(tabLayout!!.newTab().setText(team1String))
        tabLayout!!.addTab(tabLayout!!.newTab().setText(team2String))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("BOTH"))
        val viewModel = ViewModelProvider(this).get(MatchViewModel::class.java)
        viewModel.team1Name.value = team1Name
        viewModel.team2Name.value = team2Name
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = TabsdAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter
        viewPager!!.offscreenPageLimit = 3
        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) { viewPager!!.currentItem = tab.position} })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}