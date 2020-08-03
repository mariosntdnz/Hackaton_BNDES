package com.example.hackatonbndes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.hackatonbndes.R
import mehdi.sakout.aboutpage.AboutPage

class SobreFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return AboutPage(requireContext())
            .isRTL(false)
            .setDescription("*** Sobre Nós ***\nUm trio com uma paixão em comum <<Computação>>\nMario, Vitor e Guilherme\nPor meio desse aplicativos buscamos " +
                                "ajudar a sociedade nessa busca incessante de como freiar a contaminação por covid-19.\n" +
                                "Evitando filas e aglomerações por meio de reservas\nVários serviços para reserva em um único App\n\n\t\tHackaton BNDES")
            .addGroup("Olhe nosso repositório")
            .addGitHub("mariosntdnz/Hackaton_BNDES","Visite os projetos do Git")
            .create()
    }
}
