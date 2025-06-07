package denys.diomaxius.nzevents.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.navigation.LocalNavController
import denys.diomaxius.nzevents.ui.screen.components.LoadingScreen
import denys.diomaxius.nzevents.ui.screen.components.LoadingScreenError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val lazyPagingItems = viewModel.eventsPager.collectAsLazyPagingItems()
    val navHostController = LocalNavController.current

    val dateSet by viewModel.dateSet.collectAsState("all")

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    when (lazyPagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            LoadingScreen()
        }

        is LoadState.Error -> {
            LoadingScreenError(
                pagingItems = lazyPagingItems
            )
        }

        else -> {
            MainContent(
                changeLocation = { viewModel.setLocationFilter(it) },
                resetLocationFilter = { viewModel.resetLocationFilter() },
                toggleDrawer = { toggleDrawer(scope, drawerState) },
                drawerState = drawerState,
                navHostController = navHostController,
                lazyPagingItems = lazyPagingItems,
                setTodayDate = { viewModel.setTodayDate() },
                resetDate = { viewModel.resetDate() },
                setWeekDate = { viewModel.setWeekDate() },
                dateSet = dateSet
            )
        }
    }
}

@Composable
fun MainContent(
    changeLocation: (Int) -> Unit,
    resetLocationFilter: () -> Unit,
    toggleDrawer: () -> Unit,
    drawerState: DrawerState,
    navHostController: NavHostController,
    lazyPagingItems: LazyPagingItems<Event>,
    resetDate: () -> Unit,
    setTodayDate: () -> Unit,
    dateSet: String,
    setWeekDate: () -> Unit
) {
    ModalNavigationDrawer(
        drawerContent = {
            HomeDrawerContent(
                changeLocation = changeLocation,
                resetLocationFilter = resetLocationFilter,
                toggleDrawer = toggleDrawer
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    toggleDrawer = toggleDrawer,
                    setTodayDate = setTodayDate,
                    resetDate = resetDate,
                    dateSet = dateSet,
                    setWeekDate = setWeekDate
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


fun toggleDrawer(
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    scope.launch {
        if (drawerState.isClosed) {
            drawerState.open()
        } else {
            drawerState.close()
        }
    }
}