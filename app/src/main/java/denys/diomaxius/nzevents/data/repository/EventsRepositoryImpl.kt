package denys.diomaxius.nzevents.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import denys.diomaxius.nzevents.data.remote.mapper.toDomain
import denys.diomaxius.nzevents.data.remote.api.EventsFindApi
import denys.diomaxius.nzevents.data.remote.paging.EventsPagingSource
import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val api: EventsFindApi
) : EventsRepository {

    override suspend fun getEvent(id: String): Event {
        val response = api.getEvent(id)
        Log.i("Empty List", "${response.events}")
        return response.events.first().toDomain()
    }

    override fun getEventsPager(
        pageSize: Int,
        startDate: String?,
        endDate: String?
    ): Flow<PagingData<Event>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                EventsPagingSource(
                    api = api,
                    locationId = null,
                    pageSize = pageSize,
                    startDate = startDate,
                    endDate = endDate
                )
            }
        ).flow
    }

    override fun getEventsByLocationPager(
        location: Int,
        pageSize: Int,
        startDate: String?,
        endDate: String?
    ): Flow<PagingData<Event>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                EventsPagingSource(
                    api = api,
                    locationId = location,
                    pageSize = pageSize,
                    startDate = startDate,
                    endDate = endDate
                )
            }
        ).flow
    }
}