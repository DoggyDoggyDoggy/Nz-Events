package denys.diomaxius.nzevents.domain.repository

import denys.diomaxius.nzevents.domain.model.Events

interface EventsRepository {
    suspend fun getEvents(rows: Int = 10): Events
}