package com.zinafrid.task5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FirstFragment : BaseFragment(R.layout.fragment_first)
class SecondFragment : BaseFragment(R.layout.fragment_second)
class ThirdFragment : BaseFragment(R.layout.fragment_third)

abstract class BaseFragment(private val res: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(res, container, false)

        layout.findViewById<View>(R.id.bnToFirst)?.setOnClickListener {
            findNavController().navigate(R.id.to_first_frag)
        }

        layout.findViewById<View>(R.id.bnToSecond)?.setOnClickListener {
            findNavController().navigate(R.id.to_second_frag)
        }

        layout.findViewById<View>(R.id.bnToThird)?.setOnClickListener {
            findNavController().navigate(R.id.to_third_frag)
        }

        return layout
    }
}