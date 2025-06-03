package denys.diomaxius.nzevents.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeDrawerContent() {
    ModalDrawerSheet (
        modifier = Modifier.width(200.dp)
    ) {
        Column {
            Text(
                "Section 1",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
            NavigationDrawerItem(
                label = { Text("Item 1") },
                selected = false,
                onClick = { /* Handle click */ }
            )
            NavigationDrawerItem(
                label = { Text("Item 2") },
                selected = false,
                onClick = { /* Handle click */ }
            )
        }
    }
}