package com.example.hackatonbndes.database.dao

import androidx.room.*
import com.example.hackatonbndes.database.entity.ReservaEntity
import com.example.hackatonbndes.database.entity.RestauranteEntity

@Dao
interface RestauranteDao {
    // Retorna uma lista com todos os dados de todos os lugares
    @Query("SELECT * FROM restaurante")
    fun getAll() : List<RestauranteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(restauranteEntity: RestauranteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun realizarReserva(reservaEntity: ReservaEntity)

    @Query("select SUM(lugares_reservados) from reserva where CNPJ = :cnpj and horario_chegar <= :horarioInicio and horario_sair >= :horarioFim and dia = :dia and mes = :mes and ano = :ano")
    fun checkReservaExistente(cnpj : String,horarioInicio : Int,horarioFim : Int, dia : Int, mes : Int , ano : Int) : Int

    @Delete
    fun delete(restauranteEntity: RestauranteEntity)
}