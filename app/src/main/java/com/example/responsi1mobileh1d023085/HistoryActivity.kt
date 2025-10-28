package com.example.responsi1mobileh1d023085

import android.content.Intent // Ditambahkan: Import untuk kelas Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.responsi1mobileh1d023085.HistoryActivity
import com.example.responsi1mobileh1d023085.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}