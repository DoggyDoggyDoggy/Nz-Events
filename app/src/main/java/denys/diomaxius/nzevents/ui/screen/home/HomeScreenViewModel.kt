package denys.diomaxius.nzevents.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.domain.usecase.GetEventsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase
): ViewModel() {
    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    init {
        getEvents()
    }

    private fun getEvents() {
        viewModelScope.launch {
            try {
                _events.value = getEventsUseCase().events
            } catch (e: Exception) {
                Log.i("Parse Events", e.message.toString())
            }
        }
    }
}