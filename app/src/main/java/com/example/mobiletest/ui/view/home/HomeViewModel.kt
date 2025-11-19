package com.example.mobiletest.ui.view.home

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.mobiletest.data.EquipamentoRepository

class HomeViewModel : ViewModel() {

    private val _nome = mutableStateOf(EquipamentoRepository.getNome())
    val nome: State<String> = _nome

    fun atualizarNome() {
        _nome.value = EquipamentoRepository.getNome()
    }
}
