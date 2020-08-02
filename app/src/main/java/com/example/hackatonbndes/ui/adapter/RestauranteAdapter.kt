package com.example.hackatonbndes.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.hackatonbndes.R
import com.example.hackatonbndes.model.Restaurante
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

        val restaurante = restaurantes[position]

        holder.itemView.textViewNome.text = restaurante.nome
        holder.itemView.textViewHorario.text = restaurante.horarioAbrir.toString() + " as " + restaurante.horarioFechar.toString()
        holder.itemView.textViewEndereco.text = restaurante.endereco

        val sdf = SimpleDateFormat( Constantes.FORMATO_DATA , Locale.US)
        var date : Date
        holder.itemView.buttonReservar.setOnClickListener {
            exibeDialogo(restaurante)
        }
    }

    @SuppressLint("ResourceType")
    fun exibeDialogo(restaurante: Restaurante) {

        val rootView: View = context.layoutInflater.inflate(R.layout.alert_dialog_realizar_reserva, null)
        var nomeRest = rootView.findViewById<TextView>(R.id.textViewNomeRestaurante)
        var printData = rootView.findViewById<TextView>(R.id.textViewDataEscolhida)
        var buttonData = rootView.findViewById<Button>(R.id.buttonEscolherData)

        var spinnerHora = rootView.findViewById<Spinner>(R.id.spinnerHorarios)

        var opcoesSpinner = getListHorarios(restaurante.horarioAbrir,restaurante.horarioFechar)

        var adapter = ArrayAdapter<String>(rootView.context,R.layout.simple_list_item_spinner,opcoesSpinner)
        adapter.setDropDownViewResource(R.layout.simple_list_item_spinner)
        spinnerHora.adapter = adapter

        spinnerHora.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {}
        }

        nomeRest.text = restaurante.nome
        buttonData.setOnClickListener {
            val now = Calendar.getInstance()
            val sdf = SimpleDateFormat( Constantes.FORMATO_DATA , Locale.US)
            var date : Date
            date = Date()
            val dataPick = DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    date = sdf.parse("$dayOfMonth/${month+1}/$year")
                    printData.text = "  Data Selecionada : $dayOfMonth / ${month+1} / $year"

                }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)
            )
            dataPick.show()
        }

        val builder: AlertDialog.Builder = CustomAlertDialogBuilder(context)
            .customBuilder(
                rootView,
                "Ok",
                "Fechar"
            )

        val alertDialog = CustomAlertDialog(context, builder.create())
        alertDialog.customButtonPositive(R.color.verde, 18).customButtonNegative(R.color.preto, 18)
        alertDialog.show()


    }

    fun getListHorarios(horaAbrir : Int, horaFechar : Int) : List<String>{
        var rangeHoras = arrayListOf<String>()
        for (i in horaAbrir..horaFechar - 1){
            rangeHoras.add(i.toString() + " - " + (i + 1).toString() )
        }

        return rangeHoras
    }
}