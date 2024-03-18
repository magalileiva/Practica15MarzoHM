package com.utad.practica15marzohm.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.utad.practica15marzohm.R
import com.utad.practica15marzohm.databinding.ActivityMainBinding
import com.utad.practica15marzohm.ui.secondView.SecondActivity

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding: ActivityMainBinding get() = _binding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnNext.setOnClickListener {
            saveUsers()
        }
    }

    private fun saveUsers() {
        // guardo lo que recibo con el binding del objeto en el layout
        val name =  binding.etUser.text.toString().trim()
        if (!name.isNullOrEmpty()){
            viewModel.saveData(this, name) // lo guardo aqui con la funcion de guardado de la viewModel
            goToHomeWorkScreen() // navego a la siguiente pantalla
        }else{
            Toast.makeText(this, "Introduce texto", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToHomeWorkScreen() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
        finish()
    }
}