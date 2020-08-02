package com.example.hackatonbndes.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackatonbndes.common.Response
import com.example.hackatonbndes.model.Reserva
import com.example.hackatonbndes.model.Restaurante
import com.example.hackatonbndes.repository.RestauranteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class RestaurantesViewModel : ViewModel() {

    private val response = MutableLiveData<Response>()
    private val checkReserva = MutableLiveData<Response>()
    private val repository = RestauranteRepository()

    fun getRestaurantes(){

        CoroutineScope(Dispatchers.IO).launch {
            try {
                response.postValue(Response.success(repository.getAll()))
            }
            catch (t : Throwable){
                response.postValue(Response.error(Throwable("Erro")))
            }
        }
    }

    fun add(){
        CoroutineScope(Dispatchers.IO).launch {
            repository.add(Restaurante("BNDES burguer", "av presidente vargas 1200", 40, 18, 23, "00. 102. 010/2020-01"))
            repository.add(Restaurante("Sushi BNDES",   "av marechal floriano 1000", 25, 18, 22, "01. 203. 020/2020-05"))
            repository.add(Restaurante("Ob's shake",    "av passos 200",             20, 10, 16, "30. 306. 030/2020-09"))
            repository.add(Restaurante("MC batatas",    "av salvador de sá 300",     50, 10, 21, "06. 405. 040/2020-18"))
            repository.add(Restaurante("Mechelao ",     "av república do chile 150", 12, 10, 15, "08. 509. 050/2020-69"))
            repository.add(Restaurante("Dona Chiquita", "av augusto severo 100",     16, 12, 16, "10. 610. 060/2020-71"))
            repository.add(Restaurante("Boca Molhada",  "av graça aranha 600",       22, 10, 16, "50. 720. 070/2020-50"))
            repository.add(Restaurante("Em casa Chopp", "av marechal câmara 360",    30, 16, 20, "08. 890. 080/2020-45"))
            repository.add(Restaurante("Verdinho bar",  "av presidente vargas 1600", 28, 13, 17, "10. 950. 090/2020-69"))
            repository.add(Restaurante("Pepita Lanches","av general justo 400",      18, 13, 18, "20. 140. 010/2020-96"))
            repository.add(Restaurante("Acarajé Point", "av beira mar 100",          25, 11, 15, "26. 170. 020/2020-88"))
            repository.add(Restaurante("Açaí BNDES",    "av trinta e um de março 5", 35, 10, 20, "33. 190. 030/2020-47"))
            repository.add(Restaurante("Point do Vinho","Rua Ulysses Guimarães 300", 50, 19, 23, "28. 150. 040/2020-43"))
            repository.add(Restaurante("RJ goumert",    "central do brasil",         34, 15, 21, "85. 108. 050/2020-20"))
            repository.add(Restaurante("Binho lanches", "rua bueno aires 12",        33, 15, 19, "10. 199. 060/2020-55"))
            getRestaurantes()
        }

    }

    fun realizarReservas(reserva: Reserva){
        CoroutineScope(Dispatchers.IO).launch {
            repository.realizarReserva(reserva)
        }
    }

    fun checkReservaExistente(reserva : Reserva) {
        CoroutineScope(Dispatchers.IO).launch {
            checkReserva.postValue(Response.success(repository.checkReservaExistente(reserva)))
        }
    }

    fun responseReserva(): MutableLiveData<Response> {
        return checkReserva
    }

    fun response(): MutableLiveData<Response> {
        return response
    }
}