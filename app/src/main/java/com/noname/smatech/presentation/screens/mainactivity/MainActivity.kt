package com.noname.smatech.presentation.screens.mainactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.noname.smatech.R
import com.noname.smatech.presentation.screens.repositriesfragment.RepositriesFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.fram,
            RepositriesFragment(), "Main").commit()

    }
}
