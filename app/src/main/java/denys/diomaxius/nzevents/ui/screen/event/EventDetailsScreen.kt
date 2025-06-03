package denys.diomaxius.nzevents.ui.screen.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage

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

        Spacer(modifier = Modifier.height(24.dp))

        event.sessions.sessions.forEach {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
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

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
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
}
