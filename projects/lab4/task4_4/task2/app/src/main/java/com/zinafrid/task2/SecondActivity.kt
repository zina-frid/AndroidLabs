package com.zinafrid.task2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.zinafrid.task2.databinding.ActivitySecondBinding

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_FROM_THIRD && resultCode == Activity.RESULT_OK) {
            finish()
        }
    }

    private fun toThirdAct() {
        val intent = Intent(this, ThirdActivity::class.java)
        startActivityForResult(intent, REQUEST_FROM_THIRD)
    }

    private fun toAboutAct(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                startActivity(Intent(this, AboutActivity::class.java))
            }
        }
        return false
    }

    companion object {
        const val REQUEST_FROM_THIRD = 0
    }
}