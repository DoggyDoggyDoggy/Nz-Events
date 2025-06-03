package denys.diomaxius.nzevents.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.navigation.LocalNavController
import denys.diomaxius.nzevents.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val events by viewModel.events.collectAsState()
    val navHostController = LocalNavController.current

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet (
                modifier = Modifier.width(200.dp)
            ) {
                Column {
                    Text(
                        "Section 1",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    NavigationDrawerItem(
                        label = { Text("Item 1") },
                        selected = false,
                        onClick = { /* Handle click */ }
                    )
                    NavigationDrawerItem(
                        label = { Text("Item 2") },
                        selected = false,
                        onClick = { /* Handle click */ }
                    )
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    drawerState = drawerState,
                    scope = scope
                )
            }
        ) { innerPadding ->
            Content(
                modifier = Modifier.padding(innerPadding),
                events = events,
                navHostController = navHostController
            )
        }
    }
}

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
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
                onClick = {
                    scope.launch {
                        if (drawerState.isClosed) {
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}

@Composable
fun EventItemCard(
    event: Event,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp)
            .clickable {
                onItemClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = event.images.images[0].transforms.transforms.last().url,
                contentScale = ContentScale.FillWidth,
                contentDescription = "Event image"
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = event.name,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}