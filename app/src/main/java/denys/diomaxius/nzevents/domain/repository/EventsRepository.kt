package denys.diomaxius.nzevents.domain.repository

import androidx.paging.PagingData
import denys.diomaxius.nzevents.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    suspend fun getEvent(id: String): Event
    fun getEventsPager(pageSize: Int = 100): Flow<PagingData<Event>>
    fun getEventsByLocationPager(location: Int, pageSize: Int = 100): Flow<PagingData<Event>>
}