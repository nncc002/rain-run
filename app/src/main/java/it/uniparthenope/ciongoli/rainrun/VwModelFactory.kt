package it.uniparthenope.ciongoli.rainrun

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VwModelFactory(private val repo:ScoreRepo) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VwModel::class.java)) {
                return VwModel(repo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
