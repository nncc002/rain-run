package it.uniparthenope.ciongoli.rainrun

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class AppData : RoomDatabase(){
    abstract fun scoreDAO(): ScoreDAO

    companion object{
        @Volatile private var INSTANCE: AppData? = null

        fun getData(context: Context): AppData{
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    AppData::class.java,
                    "score_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also{INSTANCE= it}
            }
        }
    }
}