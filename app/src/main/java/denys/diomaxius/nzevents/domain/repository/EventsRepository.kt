package denys.diomaxius.nzevents.domain.repository

import androidx.paging.PagingData
import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.domain.model.Events
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    suspend fun getEvents(rows: Int): Events
    suspend fun getEvent(id: String): Event
    suspend fun getEventsByLocation(location: Int): Events

    //Paging 3
    fun getEventsPager(pageSize: Int = 100): Flow<PagingData<Event>>
    fun getEventsByLocationPager(location: Int, pageSize: Int = 100): Flow<PagingData<Event>>
}