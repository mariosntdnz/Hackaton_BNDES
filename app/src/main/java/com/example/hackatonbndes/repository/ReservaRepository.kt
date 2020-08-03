package com.example.hackatonbndes.repository

import com.example.hackatonbndes.database.AppDatabase
import com.example.hackatonbndes.database.entity.ReservaEntity
import com.example.hackatonbndes.model.Reserva
import com.example.hackatonbndes.model.Restaurante

class ReservaRepository {

    private val reservaDao = AppDatabase.getDatabase()!!.reservaDao()

    fun getAll() : List<Reserva>{

        return reservaDao.getAll().map {
            Reserva(it.CNPJ,it.CPF,it.lugares_reservados,it.horario_chegar,it.horario_sair,it.dia,it.mes,it.ano)
        }
    }

    fun delete(reserva : Reserva){
        reservaDao.delete(ReservaEntity(null,reserva.cpf,reserva.cnpj,reserva.qtdReservas,reserva.dia,reserva.mes,reserva.ano,reserva.horarioInicio,reserva.horaioFim))
    }

    fun getAllNomeReservas() : List<String>{
        return reservaDao.getAllNomeReservas()
    }

    fun getAllEndReservas() : List<String>{
        return reservaDao.getAllEndReservas()
    }
}