package com.example.hackatonbndes.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackatonbndes.common.Response
import com.example.hackatonbndes.model.Reserva
import com.example.hackatonbndes.repository.ReservaRepository
import com.example.hackatonbndes.repository.RestauranteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReservasViewModel : ViewModel() {
    private val repository = ReservaRepository()
    private val response = MutableLiveData<Response>()
    private val responseAllNames = MutableLiveData<Response>()
    private val responseAllEnd = MutableLiveData<Response>()

    fun getReservas(){
        CoroutineScope(Dispatchers.IO).launch {
            getAllEndReservas()
            getAllNomeReservas()
            try {
                response.postValue(Response.success(repository.getAll()))
            }
            catch (t : Throwable){
                response.postValue(Response.error(Throwable("Erro")))
            }
        }
    }

    fun getAllNomeReservas() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                responseAllNames.postValue(Response.success(repository.getAllNomeReservas()))
            }
            catch (t : Throwable){
                responseAllNames.postValue(Response.error(Throwable("Erro")))
            }
        }
    }

    fun getAllEndReservas() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                responseAllEnd.postValue(Response.success(repository.getAllEndReservas()))
            }
            catch (t : Throwable){
                responseAllEnd.postValue(Response.error(Throwable("Erro")))
            }
        }
    }

    fun delete(reserva: Reserva){
        CoroutineScope(Dispatchers.IO).launch {
            repository.delete(reserva)
        }
    }

    fun response(): MutableLiveData<Response> {
        return response
    }
    fun responseAllNames(): MutableLiveData<Response> {
        return responseAllNames
    }
    fun responseAllEnd(): MutableLiveData<Response> {
        return responseAllEnd
    }
}