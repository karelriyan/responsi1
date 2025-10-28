package com.example.responsi1mobileh1d023085

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.responsi1mobileh1d023085.databinding.ActivityPlayerBinding
import com.example.responsi1mobileh1d023085.ui.adapter.PlayerAdapter
import com.example.responsi1mobileh1d023085.ui.fragment.DetailPlayersFragment
import com.example.responsi1mobileh1d023085.viewmodel.PlayerViewModel

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private val viewModel: PlayerViewModel by viewModels()
    private val adapter = PlayerAdapter(emptyList()) { player ->
        val frag = DetailPlayersFragment.newInstance(player.id)
        frag.show(supportFragmentManager, "detail_player")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.team.observe(this) { team ->
            team?.let {
                adapter.setData(it.squad)
            }
        }

        viewModel.loadTeam()
    }
}
