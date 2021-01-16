package com.example.examen

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Productora::class,Videojuego::class],
    version = 2
)
@TypeConverters(DateTypeConverter::class)
abstract class ExamenDB : RoomDatabase(){

    abstract fun productoraDAO(): ProductoraDAO
    abstract fun videojuegoDAO(): VideojuegoDAO

    companion object{
        @Volatile
        private var INSTANCE: ExamenDB? = null

        fun getDatabase(
            context: Context): ExamenDB{
            val tempInstance = INSTANCE
            if (tempInstance!==null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExamenDB::class.java,
                        "examendb"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}