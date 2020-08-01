package com.example.hackatonbndes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackatonbndes.R
import com.example.hackatonbndes.model.Restaurante
import com.example.hackatonbndes.ui.activity.ClickReserva
import com.example.hackatonbndes.utils.CustomAlertDialog
import com.example.hackatonbndes.utils.CustomAlertDialogBuilder
import kotlinx.android.synthetic.main.recycler_view_inflar_restaurante.view.*

class RestauranteAdapter(private val restaurantes : List<Restaurante>, private val context: Context, val clickReserva: ClickReserva) : RecyclerView.Adapter<RestauranteAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_inflar_restaurante,parent,false))
    }

    override fun getItemCount() = restaurantes.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val restaurante = restaurantes[position]

        holder.itemView.textViewNome.text = restaurante.nome
        holder.itemView.textViewHorario.text = restaurante.horario
        holder.itemView.textViewEndereco.text = restaurante.endereco
        holder.itemView.buttonReservar.setOnClickListener {
            clickReserva.realizarReserva()
        }
    }
}