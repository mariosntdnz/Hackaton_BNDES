package com.example.hackatonbndes.repository

import com.example.hackatonbndes.database.AppDatabase
import com.example.hackatonbndes.database.entity.ReservaEntity
import com.example.hackatonbndes.database.entity.RestauranteEntity
import com.example.hackatonbndes.model.Reserva
import com.example.hackatonbndes.model.Restaurante
import kotlin.random.Random

class RestauranteRepository {

    private val restauranteDao = AppDatabase.getDatabase()!!.restauranteDao()

    fun getAll() : List<Restaurante>{

        return restauranteDao.getAll().map {
            Restaurante(it.nome,it.endereco,it.vagas,it.horario_abrir,it.horario_fechar,it.CNPJ)
        }
    }

    fun add(restaurante: Restaurante){
        var restauranteEntity = RestauranteEntity(restaurante.cnpj ,restaurante.nome,restaurante.endereco,restaurante.capacidade,restaurante.horarioAbrir,restaurante.horarioFechar)
        restauranteDao.add(restauranteEntity)
    }

    fun realizarReserva(reserva: Reserva){
        var reservaEntity = ReservaEntity(null,reserva.cpf,reserva.cnpj,reserva.qtdReservas,reserva.dia,reserva.mes,reserva.ano,reserva.horarioInicio,reserva.horaioFim)
        restauranteDao.realizarReserva(reservaEntity)
    }
}