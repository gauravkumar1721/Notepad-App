package com.example.notepadapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
    }
}
// lambda function are with no name
// val sqaure: (Int) ->Int = { number
// number *number
//Unit
//squar(4)
//} first int is type of data is gonna pass and 2nd in return type and Unit return means nothing is return