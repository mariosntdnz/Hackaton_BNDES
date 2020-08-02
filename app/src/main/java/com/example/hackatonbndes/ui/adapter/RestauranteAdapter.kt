package com.example.hackatonbndes.ui.adapter

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hackatonbndes.R
import com.example.hackatonbndes.model.Restaurante
import com.example.hackatonbndes.ui.activity.ClickReserva
import com.example.hackatonbndes.utils.Constantes
import com.example.hackatonbndes.utils.CustomAlertDialog
import com.example.hackatonbndes.utils.CustomAlertDialogBuilder
import kotlinx.android.synthetic.main.recycler_view_inflar_restaurante.view.*
import java.text.SimpleDateFormat
import java.util.*

class RestauranteAdapter(private val restaurantes : List<Restaurante>, private val context: Activity) : RecyclerView.Adapter<RestauranteAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_inflar_restaurante,parent,false))
    }

    override fun getItemCount() = restaurantes.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val now = Calendar.getInstance()
        val restaurante = restaurantes[position]

        holder.itemView.textViewNome.text = restaurante.nome
        holder.itemView.textViewHorario.text = restaurante.horario
        holder.itemView.textViewEndereco.text = restaurante.endereco

        val sdf = SimpleDateFormat( Constantes.FORMATO_DATA , Locale.US)
        var date : Date
        holder.itemView.buttonReservar.setOnClickListener {

            val dataPick = DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    date = sdf.parse("$dayOfMonth/${month+1}/$year")
                    println(date)

                }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)
            )

            dataPick.show()
        }
    }
    fun dialogoFiltrar(titulo: String, locais: List<String>) {

        val rootView: View = context.layoutInflater.inflate(R.layout.alert_dialog_realizar_reserva, null)
        //val spinnerLocal  = rootView.findViewById<Spinner>(R.id.spinner_local)
        //val textTitulo = rootView.findViewById<TextView>(R.id.titulo)

        //textTitulo.text = titulo

        val builder: AlertDialog.Builder = CustomAlertDialogBuilder(context)
            .customBuilder(
                rootView,
                "Ok",
                "Fechar"
            )

//        função q passa os itens escolhido no dialog
        val salvar : () -> Unit = {}

        val alertDialog = CustomAlertDialog(context, builder.create())
        alertDialog.customButtonPositive(R.color.verde, 18).customButtonNegative(R.color.preto, 18)
        alertDialog.show()

    }

}