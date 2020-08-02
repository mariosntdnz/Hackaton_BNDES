package com.example.hackatonbndes.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackatonbndes.common.Response
import com.example.hackatonbndes.model.Restaurante
import com.example.hackatonbndes.repository.RestauranteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class RestaurantesViewModel : ViewModel() {

    private val response = MutableLiveData<Response>()
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
            var r = Random

            repository.add(Restaurante("nome"+ r.nextInt(10).toString(), "endereço"+ r.nextInt(10).toString(), 10, 9, "10"+ r.nextInt(10).toString(), "CNPJ" + r.nextInt(10).toString()))
        }
    }

    fun response(): MutableLiveData<Response> {
        return response
    }
}