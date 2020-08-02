package com.example.hackatonbndes.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hackatonbndes.common.Response
import com.example.hackatonbndes.repository.ReservaRepository
import com.example.hackatonbndes.repository.RestauranteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReservasViewModel : ViewModel() {
    private val repository = ReservaRepository()
    private val response = MutableLiveData<Response>()

    fun getReservas(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                response.postValue(Response.success(repository.getAll()))
            }
            catch (t : Throwable){
                response.postValue(Response.error(Throwable("Erro")))
            }
        }
    }

    fun response(): MutableLiveData<Response> {
        return response
    }
}