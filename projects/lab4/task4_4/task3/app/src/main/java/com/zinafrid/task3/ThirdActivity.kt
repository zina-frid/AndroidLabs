package com.zinafrid.task3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.MenuItem
import com.zinafrid.task3.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bnToFirst.setOnClickListener { toFirstAct() }
        binding.bnToSecond.setOnClickListener { toSecondAct() }
        binding.navView.setOnNavigationItemSelectedListener { toAboutAct(it) }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun toFirstAct() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun toSecondAct() {
        finish()
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