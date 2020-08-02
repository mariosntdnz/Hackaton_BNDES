package com.example.hackatonbndes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackatonbndes.R
import com.example.hackatonbndes.model.Reserva
import kotlinx.android.synthetic.main.recycler_view_inflar_reserva.view.*
import kotlinx.android.synthetic.main.recycler_view_inflar_restaurante.view.*
import kotlinx.android.synthetic.main.recycler_view_inflar_restaurante.view.textViewEndereco
import kotlinx.android.synthetic.main.recycler_view_inflar_restaurante.view.textViewNome

class ReservaAdapter(private val reservas : ArrayList<Reserva>, private val context: Context) : RecyclerView.Adapter<ReservaAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_inflar_reserva,parent,false))
    }

    override fun getItemCount() = reservas.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val reserva = reservas[position]

        holder.itemView.textViewNome.text = reserva.cnpj
        holder.itemView.textViewEndereco.text = reserva.cnpj
        holder.itemView.textViewDataReserva.text = reserva.qtdReservas.toString() + " Pessoas de " + reserva.horarioInicio.toString() + " as " + reserva.horaioFim.toString() + " data : ${reserva.dia}/${reserva.mes}/${reserva.ano}"

        holder.itemView.buttonCancelar.setOnClickListener {
            reservas.remove(reservas[position])
            notifyDataSetChanged()
        }
    }
}
