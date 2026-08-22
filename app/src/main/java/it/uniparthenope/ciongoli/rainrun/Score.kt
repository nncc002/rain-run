package it.uniparthenope.ciongoli.rainrun

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score")
data class Score(
    @PrimaryKey(autoGenerate = true)
    val id: Int= 0,
    val value: Int,
)

