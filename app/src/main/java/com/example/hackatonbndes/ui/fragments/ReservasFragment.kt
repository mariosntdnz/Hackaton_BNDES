package com.example.hackatonbndes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackatonbndes.R
import com.example.hackatonbndes.common.Response
import com.example.hackatonbndes.common.Status
import com.example.hackatonbndes.model.Reserva
import com.example.hackatonbndes.ui.adapter.ReservaAdapter
import com.example.hackatonbndes.ui.viewModel.ReservasViewModel
import kotlinx.android.synthetic.main.fragment_reservas.*
import kotlinx.coroutines.delay

class ReservasFragment : DeleteReserva,Fragment() {

    private var  reservasViewModel = ReservasViewModel()
    private var  allNameRestaurante = ArrayList<String>()
    private var  allEndRestaurante = ArrayList<String>()
    private var  reservaS = ArrayList<Reserva>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        reservasViewModel.responseAllEnd().observe(this, Observer { response->processResponseEnd(response) })
        reservasViewModel.responseAllNames().observe(this, Observer { response->processResponseName(response) })
        reservasViewModel.response().observe(this, Observer { response->processResponse(response) })
        reservasViewModel.getAllNomeReservas()
        reservasViewModel.getAllEndReservas()
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

    private fun processResponseEnd(response: Response){
        when (response.status) {
            Status.SUCCESS -> responseSuccessEnd(response.data)
            Status.ERROR -> responseFailure(response.error)
            else -> throw Exception("processResponseInformacoes error")
        }
    }
    private fun processResponseName(response: Response){
        when (response.status) {
            Status.SUCCESS -> responseSuccessName(response.data)
            Status.ERROR -> responseFailure(response.error)
            else -> throw Exception("processResponseInformacoes error")
        }
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
        this.reservaS = aux
        reservasViewModel.getAllNomeReservas()
        reservasViewModel.getAllEndReservas()

        exibeReservas(aux,allNameRestaurante,allEndRestaurante)
    }
    private fun responseSuccessName(result : Any?){
        result as List<String>
        var aux = result.toCollection(ArrayList<String>())
        this.allNameRestaurante = aux

    }
    private fun responseSuccessEnd(result : Any?){
        result as List<String>
        var aux = result.toCollection(ArrayList<String>())
        this.allEndRestaurante = aux
    }

    private fun responseFailure(erro: Throwable?){
        Toast.makeText(requireContext(), "erro ao fazer consulta", Toast.LENGTH_SHORT).show()
    }

    fun exibeReservas(reservas: ArrayList<Reserva>,allName : ArrayList<String>,allEnd : ArrayList<String>){
        val adapter = ReservaAdapter(reservas,requireActivity(),allName,allEnd,this)
        recyclerViewReservas.layoutManager = LinearLayoutManager(context)
        recyclerViewReservas.adapter = adapter
    }

    override fun delete(reserva: Reserva) {
        reservasViewModel.delete(reserva)
    }

}
