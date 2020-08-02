package com.example.hackatonbndes.ui.fragments

import com.example.hackatonbndes.model.Reserva

interface RealizarReserva {
    fun realizarReserva(reserva : Reserva,capacidade: Int)
    fun checkReservaExistente(reserva: Reserva,capacidade: Int)
}