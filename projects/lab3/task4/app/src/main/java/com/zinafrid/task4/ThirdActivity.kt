package com.zinafrid.task4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.MenuItem
import com.zinafrid.task4.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toFirst.setOnClickListener { toFirstAct() }
        binding.toSecond.setOnClickListener { toSecondAct() }
        binding.bottomAbout.setOnNavigationItemSelectedListener { toAboutAct(it) }
    }

    private fun toFirstAct() {
        val intent = Intent(this, FirstActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
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