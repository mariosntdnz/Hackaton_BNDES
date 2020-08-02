package com.example.hackatonbndes.ui.fragments

import com.example.hackatonbndes.model.Reserva

interface RealizarReserva {
    fun realizarReserva(reserva : Reserva)
    fun checkReservaExistente(reserva : Reserva) : String
}