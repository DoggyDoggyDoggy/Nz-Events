package denys.diomaxius.nzevents.data.repository

import denys.diomaxius.nzevents.data.remote.mapper.toDomain
import denys.diomaxius.nzevents.data.remote.api.EventsFindApi
import denys.diomaxius.nzevents.domain.model.Events
import denys.diomaxius.nzevents.domain.repository.EventsRepository
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val api: EventsFindApi
): EventsRepository {
    override suspend fun getEvents(rows: Int): Events {
        val response = api.getEvents(rows)
        return response.toDomain()
    }
}