package denys.diomaxius.nzevents.ui.screen.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import denys.diomaxius.nzevents.domain.model.Event

@Composable
fun LoadingScreenError(
    pagingItems: LazyPagingItems<Event>
) {
    val e = pagingItems.loadState.refresh as LoadState.Error
    Text(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        text = "Error Loading: ${e.error.localizedMessage}",
        color = androidx.compose.ui.graphics.Color.Red,
        textAlign = TextAlign.Center
    )
}