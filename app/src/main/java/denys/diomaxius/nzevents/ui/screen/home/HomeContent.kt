package denys.diomaxius.nzevents.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.navigation.Screen

@Composable
fun Content(
    modifier: Modifier,
    events: List<Event>,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn {
            items(events) { event ->
                EventItemCard(
                    event = event,
                    onItemClick = {
                        navHostController.navigate(Screen.Event.createRoute(event.id)) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}