package com.example.hackatonbndes.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.hackatonbndes.database.entity.ReservaEntity
import com.example.hackatonbndes.database.entity.RestauranteEntity
import com.example.hackatonbndes.model.Reserva

@Dao
interface ReservaDao {

    @Query("SELECT * FROM reserva GROUP BY horario_chegar, CNPJ order by id")
    fun getAll() : List<ReservaEntity>

    @Query("Select s.nome from restaurante as s inner join reserva as r on r.CNPJ = s.CNPJ order by r.id")
    fun getAllNomeReservas() : List<String>

    @Query("Select  s.endereco from restaurante as s inner join reserva as r on r.CNPJ = s.CNPJ order by r.id")
    fun getAllEndReservas() : List<String>

    @Query("SELECT * FROM reserva WHERE CNPJ = (:cnpjDesejado) AND horario_chegar <= :inicioReserva AND horario_sair>= :fimReserva")
    fun getAll(cnpjDesejado : Int,inicioReserva : Int,fimReserva : Int): List<ReservaEntity>// retorna soma de todos os lugares ocupados no espbeleciemnto no horario desejado

    @Delete
    fun delete(reserva: ReservaEntity) //apaga a reserva
}