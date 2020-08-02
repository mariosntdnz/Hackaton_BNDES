package com.example.hackatonbndes.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hackatonbndes.database.entity.RestauranteEntity

@Dao
interface RestauranteDao {
    // Retorna uma lista com todos os dados de todos os lugares
    @Query("SELECT * FROM restaurante")
    fun getAll() : List<RestauranteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(restauranteEntity: RestauranteEntity)
}