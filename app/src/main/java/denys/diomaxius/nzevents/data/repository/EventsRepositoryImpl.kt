package denys.diomaxius.nzevents.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import denys.diomaxius.nzevents.data.remote.mapper.toDomain
import denys.diomaxius.nzevents.data.remote.api.EventsFindApi
import denys.diomaxius.nzevents.data.remote.paging.EventsPagingSource
import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.domain.model.Events
import denys.diomaxius.nzevents.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val api: EventsFindApi
): EventsRepository {
    override suspend fun getEvents(rows: Int): Events {
        val response = api.getEvents(rows)
        return response.toDomain()
    }

    override suspend fun getEvent(id: String): Event {
        val response = api.getEvent(id)
        return response.events.first().toDomain()
    }

    override suspend fun getEventsByLocation(location: Int): Events {
        val response = api.getEventsByLocation(location)
        return response.toDomain()
    }

    override fun getEventsPager(pageSize: Int): Flow<PagingData<Event>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                EventsPagingSource(
                    api = api,
                    locationId = null,
                    pageSize = pageSize
                )
            }
        ).flow
    }

    override fun getEventsByLocationPager(location: Int, pageSize: Int): Flow<PagingData<Event>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                EventsPagingSource(
                    api = api,
                    locationId = location,
                    pageSize = pageSize
                )
            }
        ).flow
    }
}