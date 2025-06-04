package denys.diomaxius.nzevents.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.domain.usecase.GetEventsByLocationPagerUseCase
import denys.diomaxius.nzevents.domain.usecase.GetEventsByLocationUseCase
import denys.diomaxius.nzevents.domain.usecase.GetEventsPagerUseCase
import denys.diomaxius.nzevents.domain.usecase.GetEventsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getEventsPagerUseCase: GetEventsPagerUseCase,
    private val getEventsByLocationPagerUseCase: GetEventsByLocationPagerUseCase
) : ViewModel() {
    private val locationFlow = MutableStateFlow<Int?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _eventsPager: Flow<PagingData<Event>> = locationFlow
        .flatMapLatest { locationId ->
            if (locationId == null) {
                getEventsPagerUseCase.invoke(pageSize = 100)
            } else {
                getEventsByLocationPagerUseCase.invoke(locationId, pageSize = 100)
            }
        }
        .cachedIn(viewModelScope)

    val eventsPager: Flow<PagingData<Event>> = _eventsPager

    fun setLocationFilter(id: Int) {
        locationFlow.value = id
    }

    fun resetLocationFilter() {
        locationFlow.value = null
    }
}