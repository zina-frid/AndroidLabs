package com.zinafrid.task2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.zinafrid.task2.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bnToFirst.setOnClickListener { toFirstAct() }
        binding.bnToSecond.setOnClickListener { toSecondAct() }
        binding.navView.setOnNavigationItemSelectedListener { toAboutAct(it) }
    }

    private fun toFirstAct() {
        this.setResult(Activity.RESULT_OK)
        finish()
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