package it.uniparthenope.ciongoli.rainrun

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDAO {
    @Insert suspend fun insertScore(score: Score)
@Query("SELECT MAX(value) FROM score ")
suspend fun getScore(): Int?
}