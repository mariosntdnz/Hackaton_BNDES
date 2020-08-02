package com.example.hackatonbndes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackatonbndes.R
import com.example.hackatonbndes.common.Response
import com.example.hackatonbndes.common.Status
import com.example.hackatonbndes.model.Reserva
import com.example.hackatonbndes.model.Restaurante
import com.example.hackatonbndes.ui.adapter.ReservaAdapter
import com.example.hackatonbndes.ui.adapter.RestauranteAdapter
import com.example.hackatonbndes.ui.viewModel.ReservasViewModel
import kotlinx.android.synthetic.main.fragment_reservas.*
import kotlinx.android.synthetic.main.fragment_restaurantes.*

class ReservasFragment : Fragment() {

    private  var reservasViewModel = ReservasViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        reservasViewModel.response().observe(this, Observer { response->processResponse(response) })
        reservasViewModel.getReservas()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        reservasViewModel =
                ViewModelProviders.of(this).get(ReservasViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_reservas, container, false)

        return root
    }

    private fun processResponse(response: Response){
        when (response.status) {
            Status.SUCCESS -> responseSuccess(response.data)
            Status.ERROR -> responseFailure(response.error)
            else -> throw Exception("processResponseInformacoes error")
        }
    }

    private fun responseSuccess(result : Any?){
        result as List<Reserva>
        var aux = result.toCollection(ArrayList<Reserva>())
        exibeReservas(aux)
    }

    private fun responseFailure(erro: Throwable?){
        Toast.makeText(requireContext(), "erro ao fazer consulta", Toast.LENGTH_SHORT).show()
    }

    fun exibeReservas(reservas: ArrayList<Reserva>){
        val adapter = ReservaAdapter(reservas,requireActivity())
        recyclerViewReservas.layoutManager = LinearLayoutManager(context)
        recyclerViewReservas.adapter = adapter
    }
}
