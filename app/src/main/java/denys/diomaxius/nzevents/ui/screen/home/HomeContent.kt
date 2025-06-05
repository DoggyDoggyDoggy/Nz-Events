package denys.diomaxius.nzevents.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.navigation.Screen

@Composable
fun Content(
    modifier: Modifier,
    pagingItems: LazyPagingItems<Event>,
    navHostController: NavHostController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn {
            items(count = pagingItems.itemCount) { index ->
                val event = pagingItems[index]
                event?.let {
                    EventItemCard(
                        event = it,
                        onItemClick = {
                            navHostController.navigate(
                                Screen.Event.createRoute(it.id)
                            ) {
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }

            pagingExtensions(pagingItems)
        }
    }
}
