package com.example.mobiletest.ui.view.edit

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.mobiletest.data.EquipamentoRepository

class EditViewModel : ViewModel() {

    private val _nome = mutableStateOf(EquipamentoRepository.getNome())
    val nome: State<String> = _nome

    fun alterarTexto(novoTexto: String) {
        _nome.value = novoTexto
    }

    fun salvar() {
        EquipamentoRepository.editarNome(_nome.value)
    }
}
