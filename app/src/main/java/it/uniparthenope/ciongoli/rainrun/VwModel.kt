package it.uniparthenope.ciongoli.rainrun

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
class VwModel(private val repo: ScoreRepo): ViewModel() {
    private val _score = MutableStateFlow(0)
    private val _highscore = MutableStateFlow(0)
    private val _state = MutableStateFlow(StateG.MENU)
    val score: StateFlow<Int> = _score.asStateFlow()
    val highscore: StateFlow<Int> = _highscore.asStateFlow()
    val state: StateFlow<StateG> = _state.asStateFlow()
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
    fun reset(){
        viewModelScope.launch{
            repo.resetHighScore()
            _highscore.value=0
        }
    }
    fun setState(newstate:StateG){
        _state.value = newstate
    }
    fun increaseScore(){
        _score.value= _score.value+1
    }
    fun resetScore(){
        _score.value = 0
    }
    }

