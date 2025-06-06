package denys.diomaxius.nzevents.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.domain.usecase.GetEventsByLocationPagerUseCase
import denys.diomaxius.nzevents.domain.usecase.GetEventsPagerUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getEventsPagerUseCase: GetEventsPagerUseCase,
    private val getEventsByLocationPagerUseCase: GetEventsByLocationPagerUseCase
) : ViewModel() {
    private val locationFlow = MutableStateFlow<Int?>(null)
    private val startDateFlow = MutableStateFlow<String?>(null)
    private val endDateFlow = MutableStateFlow<String?>(null)

    private val _dateSet = MutableStateFlow("all")
    val dateSet: Flow<String> = _dateSet.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _eventsPager: Flow<PagingData<Event>> = combine(
        locationFlow,
        startDateFlow,
        endDateFlow
    ) { locationId, startDate, endDate ->
        Triple(locationId, startDate, endDate)
    }.flatMapLatest { (locationId, startDate, endDate) ->
        if (locationId == null) {
            getEventsPagerUseCase(
                pageSize = 100,
                startDate = startDateFlow.value,
                endDate = endDateFlow.value
            )
        } else {
            getEventsByLocationPagerUseCase(
                location = locationId,
                pageSize = 100,
                startDate = startDateFlow.value,
                endDate = endDateFlow.value
            )
        }
    }.cachedIn(viewModelScope)

    val eventsPager: Flow<PagingData<Event>> = _eventsPager

    fun setLocationFilter(id: Int) {
        locationFlow.value = id
    }

    fun resetLocationFilter() {
        locationFlow.value = null
    }

    fun setTodayDate() {
        startDateFlow.value = LocalDate.now().toString()
        endDateFlow.value = LocalDate.now().plusDays(1).toString()
        _dateSet.value = "today"
    }

    fun resetDate() {
        startDateFlow.value = null
        endDateFlow.value = null
        _dateSet.value = "all"
    }
}