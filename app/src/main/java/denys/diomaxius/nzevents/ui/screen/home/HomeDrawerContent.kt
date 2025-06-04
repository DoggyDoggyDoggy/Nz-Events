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
import denys.diomaxius.nzevents.domain.model.City

@Composable
fun HomeDrawerContent(
    changeLocation: (Int) -> Unit,
    resetLocationFilter: () -> Unit
) {
    ModalDrawerSheet(
        modifier = Modifier.width(200.dp)
    ) {
        Column {
            Text(
                text = "Location",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
            NavigationDrawerItem(
                label = {
                    Text(
                        text = City.HAMILTON.cityName
                    )
                },
                selected = false,
                onClick = {
                    changeLocation(
                        City.HAMILTON.id
                    )
                }
            )
            NavigationDrawerItem(
                label = {
                    Text("Reset location filter")
                },
                selected = false,
                onClick = { resetLocationFilter() }
            )
        }
    }
}