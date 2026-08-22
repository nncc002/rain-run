package it.uniparthenope.ciongoli.rainrun

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
class VwModel(private val repo: ScoreRepo): ViewModel() {
    private val _highscore = MutableStateFlow(0)
    val highscore: StateFlow<Int> = _highscore.asStateFlow()
    init {
        load()
    }

    fun load(){
        viewModelScope.launch{
            _highscore.value = repo.getHighScore()?:0
            Log.d("State", "success")
        }
    }

    fun save(newscore: Int){
        viewModelScope.launch {
            repo.insert(Score(value= newscore))
            _highscore.value=newscore
        }

    }
    }

