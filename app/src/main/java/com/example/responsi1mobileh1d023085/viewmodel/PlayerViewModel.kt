package com.example.responsi1mobileh1d023085.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi1mobileh1d023085.data.model.TeamResponse
import com.example.responsi1mobileh1d023085.data.repository.TeamRepository
import com.example.responsi1mobileh1d023085.utils.Constants
import kotlinx.coroutines.launch

class PlayerViewModel : ViewModel() {
    private val repo = TeamRepository()

    private val _team = MutableLiveData<TeamResponse?>()
    val team: LiveData<TeamResponse?> = _team

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadTeam(id: Int = Constants.TEAM_ID) {
        viewModelScope.launch {
            try {
                val res = repo.fetchTeam(id)
                if (res.isSuccessful) {
                    _team.value = res.body()
                    Log.d("SUCCESS_GET_DATA", "Team loaded: ${res.body()}")
                } else {
                    _error.value = "HTTP ${res.code()}"
                    Log.e("ERROR_GET_DATA", "HTTP ${res.code()}")
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}