package com.zinafrid.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.zinafrid.task3.databinding.ActivityFirstBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bnToSecond.setOnClickListener { toSecondAct() }
        binding.navView.setOnNavigationItemSelectedListener { toAboutAct(it) }
        Log.d("CheckFlag", "OnCreate()")
    }

    private fun toSecondAct() {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    private fun toAboutAct(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                startActivity(Intent(this, AboutActivity::class.java))
            }
        }
        return false
    }
}