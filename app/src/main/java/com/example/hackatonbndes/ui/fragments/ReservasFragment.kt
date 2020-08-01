package com.example.hackatonbndes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.hackatonbndes.R
import com.example.hackatonbndes.ui.viewModel.ReservasViewModel

class ReservasFragment : Fragment() {

    private lateinit var reservasViewModel: ReservasViewModel

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
}
