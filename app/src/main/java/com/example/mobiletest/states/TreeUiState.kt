package com.example.mobiletest.states

import com.example.mobiletest.repositories.TreeNode

sealed class TreeUiState<out T> {

    data class Success<out T>(val tree: List<TreeNode>) : TreeUiState<T>()

    data class Error(val message: String, val code: Int? = null) : TreeUiState<Nothing>()

    data object Loading : TreeUiState<Nothing>()

    data object Idle : TreeUiState<Nothing>()

}