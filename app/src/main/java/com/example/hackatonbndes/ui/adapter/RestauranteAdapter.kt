package com.example.hackatonbndes.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackatonbndes.R
import com.example.hackatonbndes.model.Restaurante
import com.example.hackatonbndes.ui.activity.RealizarResevaActivity
import kotlinx.android.synthetic.main.recycler_view_inflar_restaurante.view.*

class RestauranteAdapter(private val restaurantes : List<Restaurante>, private val context: Context) : RecyclerView.Adapter<RestauranteAdapter.MyViewHolder>() {
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
            val intent = Intent(context,RealizarResevaActivity::class.java)
            intent.putExtra("nome",restaurante.nome)
            intent.putExtra("horario",restaurante.horario)
            intent.putExtra("cnpj",restaurante.cnpj)
            context.startActivity(intent)
        }
    }
}