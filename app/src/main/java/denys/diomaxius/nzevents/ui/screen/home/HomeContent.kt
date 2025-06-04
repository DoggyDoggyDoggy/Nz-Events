package denys.diomaxius.nzevents.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
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
            if (pagingItems.loadState.refresh is LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Loading...", modifier = Modifier.align(Alignment.Center))
                    }
                }
            }

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

            if (pagingItems.loadState.append is LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            if (pagingItems.loadState.refresh is LoadState.Error) {
                val e = pagingItems.loadState.refresh as LoadState.Error
                item {
                    Text(
                        "Error Loading: ${e.error.localizedMessage}",
                        color = androidx.compose.ui.graphics.Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }

            if (pagingItems.loadState.append is LoadState.Error) {
                val e = pagingItems.loadState.append as LoadState.Error
                item {
                    Text(
                        "Error load more: ${e.error.localizedMessage}",
                        color = androidx.compose.ui.graphics.Color.Red,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}