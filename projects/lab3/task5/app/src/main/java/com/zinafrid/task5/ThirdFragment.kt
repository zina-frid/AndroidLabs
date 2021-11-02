package com.zinafrid.task5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zinafrid.task5.databinding.FragmentThirdBinding

class ThirdFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentThirdBinding.inflate(layoutInflater)
        val navController = findNavController()

        binding.toFirst.setOnClickListener {
            navController.navigate(R.id.third_frag_to_first_frag)
        }
        binding.toSecond.setOnClickListener {
            navController.navigate(R.id.third_frag_to_second_frag)
        }

        binding.bottomAbout.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.about -> {
                    navController.navigate(R.id.global_about)
                }
            }
            false
        }
        return binding.root
    }
}