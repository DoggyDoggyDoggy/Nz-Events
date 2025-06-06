package denys.diomaxius.nzevents.domain.repository

import androidx.paging.PagingData
import denys.diomaxius.nzevents.domain.model.Event
import kotlinx.coroutines.flow.Flow
import kotlin.String

interface EventsRepository {
    suspend fun getEvent(id: String): Event

    fun getEventsPager(
        pageSize: Int = 100, startDate: String?,
        endDate: String?
    ): Flow<PagingData<Event>>

    fun getEventsByLocationPager(
        location: Int, pageSize: Int = 100, startDate: String?,
        endDate: String?
    ): Flow<PagingData<Event>>
}