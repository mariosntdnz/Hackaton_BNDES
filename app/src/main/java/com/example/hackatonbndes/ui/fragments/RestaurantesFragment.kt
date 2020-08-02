package com.example.hackatonbndes.ui.fragments

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackatonbndes.R
import com.example.hackatonbndes.common.Response
import com.example.hackatonbndes.common.Status
import com.example.hackatonbndes.model.Restaurante
import com.example.hackatonbndes.ui.adapter.RestauranteAdapter
import com.example.hackatonbndes.ui.viewModel.RestaurantesViewModel
import kotlinx.android.synthetic.main.fragment_restaurantes.*

class RestaurantesFragment : Fragment() {

    private val restaurantesViewModel = RestaurantesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*val pref =
            requireActivity().applicationContext.getSharedPreferences("createBD", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("initDB", true);
        editor.commit()*/

        restaurantesViewModel.add()

        restaurantesViewModel.response().observe(this, Observer { response->processResponse(response) })
        restaurantesViewModel.getRestaurantes()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_restaurantes, container, false)
    }

    private fun exibeRestaurantes(restaurantes: List<Restaurante>){

        val adapter = RestauranteAdapter(restaurantes,requireActivity())
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

    private fun responseSuccess(result : Any?){
        result as List<Restaurante>
        exibeRestaurantes(result)
    }

    private fun responseFailure(erro: Throwable?){
        Toast.makeText(requireContext(), "erro ao fazer consulta", Toast.LENGTH_SHORT).show()
    }
}
