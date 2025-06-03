package denys.diomaxius.nzevents.ui.screen.event

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import denys.diomaxius.nzevents.domain.model.Event

@Composable
fun EventDetailsScreen(
    viewModel: EventDetailsScreenViewModel = hiltViewModel()
) {
    val event by viewModel.event.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
            .padding(horizontal = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = event.name,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(),
            model = event.images.images[0].transforms.transforms.last().url,
            contentScale = ContentScale.FillWidth,
            contentDescription = "Image"
        )

        Spacer(modifier = Modifier.height(24.dp))

        EventDescription(event)

        Spacer(modifier = Modifier.height(24.dp))

        EventAddress(event)

        EventDates(event)
    }
}

@Composable
fun EventDescription(
    event: Event
) {
    Text(
        text = "Description:",
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = event.description,
        fontSize = 16.sp
    )
}

@Composable
fun EventAddress(
    event: Event
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Address"
        )

        Text(
            text = event.address,
            fontSize = 16.sp
        )
    }
}

@Composable
fun EventDates(
    event: Event
) {
    var expanded by remember { mutableStateOf(false) }

    event.sessions
        .sessions
        .getOrNull(0)
        ?.datetimeSummary
        ?.let {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date"
                )
                Text(
                    text = it,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(12.dp))

                if (event.sessions.sessions.size > 1){
                    Icon(
                        modifier = Modifier.clickable {
                            expanded = !expanded
                        },
                        imageVector =
                            if (!expanded) Icons.Default.KeyboardArrowDown
                            else Icons.Default.KeyboardArrowUp,
                        contentDescription = "Date"
                    )
                }
            }
        }

    AnimatedVisibility(
        visible = expanded,
        enter = expandVertically(
            animationSpec = tween(durationMillis = 350)
        ),
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = 350)
        )
    ) {
        Column {
            event.sessions.sessions.drop(1).forEach {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date"
                    )

                    Text(
                        text = it.datetimeSummary,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}