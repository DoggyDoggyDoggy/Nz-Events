package denys.diomaxius.nzevents.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.rememberCoroutineScope
import androidx.paging.compose.collectAsLazyPagingItems
import denys.diomaxius.nzevents.navigation.LocalNavController

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val lazyPagingItems = viewModel.eventsPager.collectAsLazyPagingItems()
    val navHostController = LocalNavController.current

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            HomeDrawerContent(
                changeLocation = { viewModel.setLocationFilter(it) },
                resetLocationFilter = { viewModel.resetLocationFilter() }
            )
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
                navHostController = navHostController,
                pagingItems = lazyPagingItems
            )
        }
    }
}