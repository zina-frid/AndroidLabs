package com.zinafrid.task5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zinafrid.task5.databinding.FragmentSecondBinding

class SecondFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSecondBinding.inflate(layoutInflater)
        val navController = findNavController()
        binding.toFirst.setOnClickListener {
            navController.navigate(R.id.second_frag_to_first_frag)
        }
        binding.toThird.setOnClickListener {
            navController.navigate(R.id.second_frag_to_third_frag)
        }

        binding.bottomAbout.setOnNavigationItemSelectedListener {
            (activity as MainActivity).toAboutAct(it)
        }
        return binding.root
    }
}