package denys.diomaxius.nzevents.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    toggleDrawer: () -> Unit,
    setTodayDate: () -> Unit,
    resetDate: () -> Unit,
    dateSet: String
) {
    Column {
        CenterAlignedTopAppBar(
            modifier = modifier,
            title = {
                Text(
                    text = "NZ Events",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = toggleDrawer

                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Localized description"
                    )
                }
            }
        )
        DatesButtons(
            setTodayDate = setTodayDate,
            resetDate = resetDate,
            dateSet = dateSet
        )
    }
}

@Composable
fun DatesButtons(
    setTodayDate: () -> Unit,
    resetDate: () -> Unit,
    dateSet: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp, start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Card(
            modifier = Modifier
                .width(72.dp)
                .clickable { setTodayDate() },
            colors = if (dateSet == "today") CardDefaults.cardColors(
                containerColor = Color(0xFF9679C7)
            ) else CardDefaults.cardColors()
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = "Today",
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        }

        Card(
            modifier = Modifier
                .width(54.dp)
                .clickable { resetDate() },
            colors = if (dateSet == "all") CardDefaults.cardColors(
                containerColor = Color(0xFF9679C7)
            ) else CardDefaults.cardColors()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                text = "All",
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}