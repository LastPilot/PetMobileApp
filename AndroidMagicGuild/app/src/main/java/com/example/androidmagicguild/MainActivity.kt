package com.example.androidmagicguild

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.androidmagicguild.databinding.ActivityMainBinding

val petAdapter = PetAdapter()

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        petAdapter.retrieveData()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
}
