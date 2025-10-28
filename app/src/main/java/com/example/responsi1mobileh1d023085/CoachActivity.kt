package com.example.responsi1mobileh1d023085

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.responsi1mobileh1d023085.databinding.ActivityCoachBinding
import com.example.responsi1mobileh1d023085.viewmodel.PlayerViewModel

class CoachActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoachBinding
    private val vm: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.coach) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        vm.team.observe(this) { t ->
            val c = t?.coach
            binding.tvCoachName.text = c?.name ?: "-"
            binding.tvCoachDob.text = c?.dateOfBirth ?: "-"
            binding.tvCoachNation.text = c?.nationality ?: "-"
        }

        vm.loadTeam()
    }
}
