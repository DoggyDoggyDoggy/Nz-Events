package denys.diomaxius.nzevents.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import denys.diomaxius.nzevents.domain.model.City

@Composable
fun HomeDrawerContent(
    changeLocation: (Int) -> Unit,
    resetLocationFilter: () -> Unit,
    toggleDrawer: () -> Unit
) {
    ModalDrawerSheet(
        modifier = Modifier.width(200.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Location",
                modifier = Modifier.padding(16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            CityNavDrawItem(
                changeLocation = changeLocation,
                closeDrawer = toggleDrawer
            )

            NavigationDrawerItem(
                label = {
                    Text(
                        text = "Reset location filter",
                        fontSize = 18.sp
                    )
                },
                selected = false,
                onClick = {
                    resetLocationFilter()
                    toggleDrawer()
                }
            )
        }
    }
}

@Composable
fun CityNavDrawItem(
    changeLocation: (Int) -> Unit,
    closeDrawer: () -> Unit
) {
    for (city in City.entries) {
        NavigationDrawerItem(
            label = {
                Text(
                    text = city.cityName,
                    fontSize = 18.sp
                )
            },
            selected = false,
            onClick = {
                changeLocation(city.id)
                closeDrawer()
            }
        )
    }
}