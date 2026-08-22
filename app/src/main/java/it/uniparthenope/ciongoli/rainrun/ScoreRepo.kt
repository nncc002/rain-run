package it.uniparthenope.ciongoli.rainrun

class ScoreRepo(private val dao: ScoreDAO) {
    suspend fun insert(score: Score) = dao.insertScore(score)
    suspend fun getHighScore():Int? = dao.getScore()
}