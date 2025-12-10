package com.example.mobiletest.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiletest.repositories.TreeNode
import com.example.mobiletest.repositories.TreeRepository
import com.example.mobiletest.states.TreeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TreeViewModel @Inject constructor(
    private val repository: TreeRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val  _uiState = MutableStateFlow<TreeUiState<List<TreeNode>>>(TreeUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _tree = MutableStateFlow<List<TreeNode>?>(null)
    val tree = _tree.asStateFlow()

    private val _username = savedStateHandle.get<String>("username") ?: ""
    val username = _username


    fun getTree() {
        viewModelScope.launch {
            val token = savedStateHandle.get<String>("token")

            if (token != null) {
                _uiState.value = TreeUiState.Loading

                val result = repository.getTree(
                    token = "Bearer $token",
                    siteId = 20640
                )
                _uiState.value = result
            } else {
                _uiState.value = TreeUiState.Error("Token não encontrado")
            }
        }
    }

    fun updateState(newState: TreeUiState<List<TreeNode>>) {
        _uiState.value = newState
    }

}