package com.example.mobiletest.ui.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mobiletest.repositories.TreeNode
import com.example.mobiletest.states.TreeUiState
import com.example.mobiletest.ui.EditBottomSheet
import com.example.mobiletest.ui.TreeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreeScreen(
    modifier: Modifier = Modifier,
    treeViewModel: TreeViewModel = hiltViewModel(),
    navController: NavController,
    token: String,
    username: String
) {

    LaunchedEffect(Unit) {
        treeViewModel.setToken(token)
        treeViewModel.setUsername(username)
        treeViewModel.getTree()
    }

    val uiState = treeViewModel.uiState.collectAsState()
    var showTree by remember { mutableStateOf(false) }
    var treeData: List<TreeNode>? by remember { mutableStateOf(null) }

    LaunchedEffect(uiState.value) {
        when (val state = uiState.value) {
            is TreeUiState.Success<*> -> {
                showTree = true
                treeData = state.tree
                treeViewModel.updateState(TreeUiState.Idle)
            }
            is TreeUiState.Error -> {
                Log.e("Tree Error", "Mensagem: ${state.message} | Código: ${state.code}")
                treeViewModel.updateState(TreeUiState.Idle)
            }
            else -> {}
        }
    }

    Box(modifier = modifier.fillMaxSize()) {

        // 🔹 HOME NORMAL
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFF325F))
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp)
                    .clickable {
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(Modifier.height(1.dp))

            Column(
                modifier = Modifier
                    .background(Color(0xFFFF325F))
                    .fillMaxWidth()
                    .height(170.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.padding(start = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Hello", fontSize = 25.sp, color = Color.White)
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = username.lowercase().substringBefore(".").replaceFirstChar { it.uppercase() },
                        fontSize = 28.sp,
                        color = Color.White
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showTree) {
                    LazyColumn(
                        modifier = Modifier.padding(start = 12.dp, top = 24.dp)
                    ) {
                        items(treeData ?: emptyList()) { node ->
                            TreeNodeItem(node, navController, treeViewModel)
                        }
                    }
                }
            }
        }

        // 🔹 BOTTOM SHEET FLUTUANTE
        EditBottomSheet(treeViewModel)
    }
}


@Composable
fun TreeNodeItem(
    node: TreeNode,
    navController: NavController,
    treeViewModel: TreeViewModel
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .combinedClickable(
                    onClick = {
                        if (node.children.isNotEmpty())
                            expanded = !expanded
                    },
                    onLongClick = {
                        treeViewModel.equipmentName.value = node.name
                        treeViewModel.selectedNodeId.value = node.id.toString()
                        treeViewModel.showEditBottomSheet.value = true
                    }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val icon = when {
                node.children.isEmpty() -> Icons.Default.Sensors
                expanded -> Icons.Default.FolderOpen
                else -> Icons.Default.Folder
            }

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = if (node.tag.isNullOrEmpty()) node.name
                else "${node.name} - ${node.tag}",
                fontSize = 16.sp
            )
        }

        if (expanded && node.children.isNotEmpty()) {
            Column(modifier = Modifier.padding(start = 24.dp)) {
                node.children.forEach { child ->
                    TreeNodeItem(child, navController, treeViewModel)  
                }
            }
        }
    }
}
