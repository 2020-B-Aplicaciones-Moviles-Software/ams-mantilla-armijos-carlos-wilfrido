package com.example.examen

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductoraDAO {

    @Query("SELECT * FROM productora")
    fun getAll(): LiveData<List<Productora>>

    @Query("SELECT * FROM Productora WHERE idProd = :idProd")
    fun getById(idProd: Int): LiveData<Productora>

    @Update
    fun update(productora: Productora)

    @Insert
    fun insertAll(vararg productora: Productora)

    @Delete
    fun delete(productora: Productora)

    @Transaction
    @Query("SELECT * FROM Productora WHERE idProd = :idProd")
    fun getProdVid(idProd: Int): List<ProductoraVideojuego>

}