import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobiletest.repositories.TreeNode
import com.example.mobiletest.ui.TreeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TreeNodeItem(
    node: TreeNode,
    treeViewModel: TreeViewModel
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .combinedClickable(
                    onClick = {
                        if (node.children.isNotEmpty()) {
                            expanded = !expanded
                        }
                    },
                    onLongClick = {
                        treeViewModel.openEditBottomSheet(node)
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

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = if (node.tag.isNullOrEmpty()) {
                    node.name
                } else {
                    "${node.name} - ${node.tag}"
                },
                fontSize = 16.sp
            )
        }

        if (expanded && node.children.isNotEmpty()) {
            Column(modifier = Modifier.padding(start = 24.dp)) {
                node.children.forEach { child ->
                    TreeNodeItem(
                        node = child,
                        treeViewModel = treeViewModel
                    )
                }
            }
        }
    }
}
