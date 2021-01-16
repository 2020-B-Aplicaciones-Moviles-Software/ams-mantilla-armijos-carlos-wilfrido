package com.example.examen

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VideojuegoDAO {
    @Query("SELECT * FROM videojuego")
    fun getAll(): LiveData<List<Videojuego>>

    @Query("SELECT * FROM Videojuego WHERE idVid = :idVid")
    fun getById(idVid: Int): LiveData<Videojuego>

    @Update
    fun update(videojuego: Videojuego)

    @Insert
    fun insert(vararg videojuego: Videojuego)

    @Delete
    fun delete(videojuego: Videojuego)

    @Query("SELECT * FROM videojuego WHERE idProd = :idProd")
    fun getAllVideojuegoPorProductora(idProd:Int): LiveData<List<Videojuego>>
}