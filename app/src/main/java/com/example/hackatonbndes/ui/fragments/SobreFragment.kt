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
import com.example.hackatonbndes.ui.viewModel.SobreViewModel

class SobreFragment : Fragment() {

    private lateinit var sobreViewModel: SobreViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        sobreViewModel =
                ViewModelProviders.of(this).get(SobreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sobre, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        /*sobreViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }
}
