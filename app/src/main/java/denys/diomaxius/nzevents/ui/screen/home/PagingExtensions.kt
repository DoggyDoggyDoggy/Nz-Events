package denys.diomaxius.nzevents.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import denys.diomaxius.nzevents.domain.model.Event

fun LazyListScope.pagingExtensions(
    pagingItems: LazyPagingItems<Event>
) {
    when {
        pagingItems.loadState.append is LoadState.Loading -> {
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

        pagingItems.loadState.append is LoadState.Error -> {
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