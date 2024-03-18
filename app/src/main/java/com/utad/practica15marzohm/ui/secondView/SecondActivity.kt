package com.utad.practica15marzohm.ui.secondView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.utad.practica15marzohm.R
import com.utad.practica15marzohm.databinding.ActivitySecondBinding
import com.utad.practica15marzohm.ui.adapter.TareasHMAdapter

class SecondActivity : AppCompatActivity() {

    private lateinit var _binding: ActivitySecondBinding
    private val binding: ActivitySecondBinding get() = _binding
    // 1 creo el adapter (y lo importo)
    private val adapter = TareasHMAdapter()
    // instancio el viewModel
    private val viewModel: SeconActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2 hago los binding del adapter
        binding.rvTareasHM.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvTareasHM.adapter = adapter

        viewModel.getUserName(this)
        observeViewModel()
    }
    private fun observeViewModel() {
        // en este caso solo recupero el user name, pero podria recuperar lo que quisiera
        viewModel.userName.observe(this){userName ->
            if(userName!=null){
                viewModel.getHomeWork(userName) // le paso lo que la funcion me pedia
                // en caso que lo recuperado lo tenga que mostrar en esta vista ej: binding.tv.text=userName
            }
        }
        viewModel.uiState.observe(this){ uiState ->
            if (uiState.info!=null){
                adapter.submitList(uiState.info)
            }
            if (uiState.isLoading){
                //mostrar carga
            }else{
                //ocultar carga
            }
            if (uiState.error){
                // mensaje de error
            }
        }
    }
}