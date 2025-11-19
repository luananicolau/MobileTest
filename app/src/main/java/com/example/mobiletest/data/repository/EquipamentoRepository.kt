package com.example.mobiletest.data

object EquipamentoRepository {

    private var nomeEquipamento: String = "Motor"

    fun getNome(): String {
        return nomeEquipamento
    }

    fun editarNome(novoNome: String) {
        nomeEquipamento = novoNome
    }
}