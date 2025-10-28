package com.example.responsi1mobileh1d023085.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.responsi1mobileh1d023085.databinding.FragmentDetailPlayersBinding
import com.example.responsi1mobileh1d023085.viewmodel.PlayerViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailPlayersFragment : BottomSheetDialogFragment() {
    private var _b: FragmentDetailPlayersBinding? = null
    private val b get() = _b!!
    private val vm: PlayerViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _b = FragmentDetailPlayersBinding.inflate(inflater, container, false)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val playerId = arguments?.getInt(ARG_PLAYER_ID)

        vm.team.observe(viewLifecycleOwner) { team ->
            val player = team?.squad?.firstOrNull { it.id == playerId } ?: team?.squad?.firstOrNull()

            b.tvName.text = player?.name ?: "-"
            b.tvBirth.text = player?.dateOfBirth ?: "-"
            b.tvNation.text = player?.nationality ?: "-"
            b.tvPos.text = player?.position ?: "-"
        }
    }

    override fun onDestroyView() {
        _b = null
        super.onDestroyView()
    }

    companion object {
        private const val ARG_PLAYER_ID = "player_id"

        fun newInstance(playerId: Int?): DetailPlayersFragment = DetailPlayersFragment().apply {
            arguments = Bundle().apply { if (playerId != null) putInt(ARG_PLAYER_ID, playerId) }
        }
    }
}
