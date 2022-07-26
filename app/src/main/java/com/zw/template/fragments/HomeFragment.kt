package com.zw.template.fragments

import android.os.Bundle

class HomeFragment : BaseFragment() {
    var arg1: String = ""

    fun newInstance(arg1: String): HomeFragment {
        val args = Bundle()
        args.putString("ARGUMENT_NAME", arg1)
        val fragment = HomeFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arg1 = arguments?.getString("ARGUMENT_NAME", "") ?: ""
    }
}