package com.example.hackatonbndes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackatonbndes.R
import com.example.hackatonbndes.common.Response
import com.example.hackatonbndes.common.Status
import com.example.hackatonbndes.model.Reserva
import com.example.hackatonbndes.model.Restaurante
import com.example.hackatonbndes.ui.adapter.RestauranteAdapter
import com.example.hackatonbndes.ui.viewModel.RestaurantesViewModel
import kotlinx.android.synthetic.main.fragment_restaurantes.*

class RestaurantesFragment : RealizarReserva,InfoCliente,Fragment() {

    private val restaurantesViewModel = RestaurantesViewModel()
    private var capacidade = 0
    private lateinit var reserva : Reserva

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*val pref =
            requireActivity().applicationContext.getSharedPreferences("createBD", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("initDB", true);
        editor.commit()*/

        restaurantesViewModel.add()

        restaurantesViewModel.response().observe(this, Observer { response->processResponse(response) })
        restaurantesViewModel.responseReserva().observe(this, Observer { response->processCheckReserva(response)})
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_restaurantes, container, false)
    }

    private fun exibeRestaurantes(restaurantes: List<Restaurante>){

        val adapter = RestauranteAdapter(restaurantes,requireActivity(),this,this,this)
        recyclerViewRestaurantes.layoutManager = LinearLayoutManager(context)
        recyclerViewRestaurantes.adapter = adapter
    }
    private fun processResponse(response: Response){
        when (response.status) {
            Status.SUCCESS -> responseSuccess(response.data)
            Status.ERROR -> responseFailure(response.error)
            else -> throw Exception("processResponseInformacoes error")
        }
    }
    private fun processCheckReserva(response: Response){
        when (response.status) {
            Status.SUCCESS -> responseSuccessCheckReserva(response.data)
            Status.ERROR -> responseFailure(response.error)
            else -> throw Exception("processResponseInformacoes error")
        }
    }

    private fun responseSuccessCheckReserva(result : Any?){
        result as Int
        if(result > capacidade){
            Toast.makeText(requireContext(), "Não temos vagas disponíveis no horário selecionado", Toast.LENGTH_SHORT).show()
        }
        else{
            restaurantesViewModel.realizarReservas(reserva)
            Toast.makeText(requireContext(), "Parabéns! Reserva Efetuada Com Sucesso", Toast.LENGTH_SHORT).show()
        }
    }

    private fun responseSuccess(result : Any?){
        result as List<Restaurante>
        exibeRestaurantes(result)
    }

    private fun responseFailure(erro: Throwable?){
        Toast.makeText(requireContext(), "erro ao fazer consulta", Toast.LENGTH_SHORT).show()
    }

    override fun realizarReserva(reserva: Reserva,capacidade: Int){
        checkReservaExistente(reserva,capacidade)
    }

    override fun checkReservaExistente(reserva: Reserva,capacidade: Int) {
        restaurantesViewModel.checkReservaExistente(reserva)
        this.capacidade = capacidade
        this.reserva = reserva
    }

    override fun getCpfCliente(): String {
        return "123.123.123.12"
    }
}
