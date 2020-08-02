package com.example.hackatonbndes.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.hackatonbndes.R
import com.example.hackatonbndes.model.Reserva
import com.example.hackatonbndes.model.Restaurante
import com.example.hackatonbndes.ui.fragments.InfoCliente
import com.example.hackatonbndes.ui.fragments.RealizarReserva
import com.example.hackatonbndes.utils.Constantes
import com.example.hackatonbndes.utils.CustomAlertDialog
import com.example.hackatonbndes.utils.CustomAlertDialogBuilder
import kotlinx.android.synthetic.main.recycler_view_inflar_restaurante.view.*
import java.text.SimpleDateFormat
import java.util.*

class RestauranteAdapter(private val restaurantes : List<Restaurante>,
                         private val activityContext: Activity,
                         private val fragmentContext : Fragment,
                         private val realizarReservaInterface: RealizarReserva,
                         private val infoClienteInterface : InfoCliente) : RecyclerView.Adapter<RestauranteAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(activityContext).inflate(R.layout.recycler_view_inflar_restaurante,parent,false))
    }

    override fun getItemCount() = restaurantes.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val restaurante = restaurantes[position]

        holder.itemView.textViewNome.text = restaurante.nome
        holder.itemView.textViewHorario.text = restaurante.horarioAbrir.toString() + " as " + restaurante.horarioFechar.toString()
        holder.itemView.textViewEndereco.text = restaurante.endereco

        var reserva : Reserva?

        holder.itemView.buttonReservar.setOnClickListener {
            insertReservaFromDialog(restaurante)
        }
    }

    fun insertReservaFromDialog(restaurante: Restaurante) : Reserva?{
        return exibeDialogo(restaurante)
    }

    @SuppressLint("ResourceType")
    fun exibeDialogo(restaurante: Restaurante) : Reserva? {

        var reserva : Reserva?
        reserva = null
        var dia = 0
        var mes = 0
        var ano = 0

        val rootView: View = activityContext.layoutInflater.inflate(R.layout.alert_dialog_realizar_reserva, null)
        var nomeRest = rootView.findViewById<TextView>(R.id.textViewNomeRestaurante)
        var printData = rootView.findViewById<TextView>(R.id.textViewDataEscolhida)
        var buttonData = rootView.findViewById<Button>(R.id.buttonEscolherData)
        var numPessoasReserva = rootView.findViewById<EditText>(R.id.edittextNumPessoas)

        var spinnerHora = rootView.findViewById<Spinner>(R.id.spinnerHorarios)

        var opcoesSpinner = getListHorarios(restaurante.horarioAbrir,restaurante.horarioFechar)

        var adapter = ArrayAdapter<String>(rootView.context,R.layout.simple_list_item_spinner,opcoesSpinner)
        adapter.setDropDownViewResource(R.layout.simple_list_item_spinner)
        spinnerHora.adapter = adapter

        nomeRest.text = restaurante.nome
        buttonData.setOnClickListener {
            val now = Calendar.getInstance()
            val sdf = SimpleDateFormat( Constantes.FORMATO_DATA , Locale.US)
            var date : Date
            date = Date()
            val dataPick = DatePickerDialog(
                activityContext,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    date = sdf.parse("$dayOfMonth/${month+1}/$year")
                    dia = dayOfMonth
                    mes = month + 1
                    ano = year
                    printData.text = "  Data Selecionada : $dayOfMonth / ${month+1} / $year"

                }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)
            )
            dataPick.show()
        }

        val builder: AlertDialog.Builder = CustomAlertDialogBuilder(activityContext)
            .customBuilder(
                rootView,
                "Reservar",
                "Fechar"
            )

        val alertDialog = CustomAlertDialog(activityContext, builder.create())
        alertDialog.customButtonPositive(R.color.verde, 18).customButtonNegative(R.color.preto, 18)
        alertDialog.show()

        alertDialog.positiveClick.observe(fragmentContext.viewLifecycleOwner, Observer {
            if (it == true){
                var dismiss = true
                var auxHoraSpinner = spinnerHora.selectedItem.toString()
                var auxHoraInicio = 0
                var auxHoraFim = 0
                if(numPessoasReserva.text.toString() == "") {
                    dismiss = false
                    Toast.makeText(fragmentContext.requireContext(), "Por favor informe o número de pessoas", Toast.LENGTH_SHORT).show()
                }
                if(dia == 0 || mes == 0 || ano == 0) {
                    dismiss = false
                    Toast.makeText(fragmentContext.requireContext(), "Por favor Selecione uma data", Toast.LENGTH_SHORT).show()
                }
                else{
                    //formato intervalo hora >>> hora - hora >> ex : 15 - 16
                    auxHoraInicio = auxHoraSpinner.substring(0,2).toInt()
                    auxHoraFim = auxHoraSpinner.substring(5).toInt()
                }
                if (dismiss == true) {
                    reserva = Reserva(restaurante.cnpj,infoClienteInterface.getCpfCliente(),numPessoasReserva.text.toString().toInt(),auxHoraInicio,auxHoraFim,dia,mes,ano)
                    realizarReservaInterface.realizarReserva(reserva!!,restaurante.capacidade - numPessoasReserva.text.toString().toInt())
                }
            }
        })

        return reserva
    }

    fun getListHorarios(horaAbrir : Int, horaFechar : Int) : List<String>{
        var rangeHoras = arrayListOf<String>()
        for (i in horaAbrir..horaFechar - 1){
            var auxInicio : String
            var auxFim : String
            if(i.toString().length == 2){
                auxInicio = i.toString()
            }
            else{
                auxInicio = "0"+i.toString()
            }
            if((i+1).toString().length == 2){
                auxFim = (i+1).toString()
            }
            else{
                auxFim = "0"+i.toString()
            }

            rangeHoras.add(auxInicio + " - " + auxFim )
        }

        return rangeHoras
    }
}