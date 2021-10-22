package com.zinafrid.task5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zinafrid.task5.databinding.FragmentFirstBinding

class FirstFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFirstBinding.inflate(layoutInflater)
        val navController = findNavController()
        binding.toSecond.setOnClickListener {
            navController.navigate(R.id.first_frag_to_second_frag)
        }

        binding.bottomAbout.setOnNavigationItemSelectedListener {
            (activity as MainActivity).toAboutAct(it)
        }
        return binding.root
    }
}