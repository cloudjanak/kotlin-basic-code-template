package com.zw.template.activities

import android.os.Bundle
import com.zw.template.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }
}