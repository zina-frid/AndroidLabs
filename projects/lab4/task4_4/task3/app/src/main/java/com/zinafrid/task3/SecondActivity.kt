package com.zinafrid.task3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.zinafrid.task3.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bnToFirst.setOnClickListener { toFirstAct() }
        binding.bnToThird.setOnClickListener { toThirdAct() }
        binding.navView.setOnNavigationItemSelectedListener { toAboutAct(it) }
    }

    private fun toFirstAct() {
        finish()
    }

    private fun toThirdAct() {
        val intent = Intent(this, ThirdActivity::class.java)
        startActivity(intent)
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