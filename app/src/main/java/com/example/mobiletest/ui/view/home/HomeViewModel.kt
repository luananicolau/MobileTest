package com.example.mobiletest.ui

import androidx.compose.runtime.mutableStateOf
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
) : ViewModel() {


    private val _uiState =
        MutableStateFlow<TreeUiState<List<TreeNode>>>(TreeUiState.Idle)
    val uiState = _uiState.asStateFlow()


    private val _username = MutableStateFlow(
        savedStateHandle["username"] ?: ""
    )
    val username = _username.asStateFlow()

    val showEditBottomSheet = mutableStateOf(false)
    val equipmentName = mutableStateOf("")
    val selectedNodeId = mutableStateOf<Int?>(null)

    fun setUsername(value: String) {
        _username.value = value
        savedStateHandle["username"] = value
    }

    fun setToken(token: String) {
        savedStateHandle["token"] = token
    }

    fun getTree() {
        viewModelScope.launch {
            val token = savedStateHandle.get<String>("token")

            if (token.isNullOrBlank()) {
                _uiState.value = TreeUiState.Error("Token não encontrado ou inválido")
                return@launch
            }

            _uiState.value = TreeUiState.Loading

            val result = repository.getTree(
                token = "Bearer $token",
                siteId = 20640
            )

            _uiState.value = result

            if (result is TreeUiState.Success) {
                android.util.Log.d("GET_TREE", "Árvore carregada com sucesso!")
            }
        }
    }

    fun openEditBottomSheet(node: TreeNode) {
        selectedNodeId.value = node.id
        equipmentName.value = node.name
        showEditBottomSheet.value = true
    }

    fun closeEditBottomSheet() {
        showEditBottomSheet.value = false
        selectedNodeId.value = null
    }

    fun setEquipmentName(value: String) {
        equipmentName.value = value
    }

    fun saveEquipmentName() {
        val nodeId = selectedNodeId.value ?: return
        val rawToken = savedStateHandle.get<String>("token") ?: ""
        val token = "Bearer $rawToken"

        viewModelScope.launch {
            repository.updateNodeName(
                token = token,
                nodeId = nodeId,
                newName = equipmentName.value
            )
            getTree()
            closeEditBottomSheet()
        }
    }
}
