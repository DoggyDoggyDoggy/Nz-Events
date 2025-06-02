package denys.diomaxius.nzevents.domain.repository

import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.domain.model.Events

interface EventsRepository {
    suspend fun getEvents(rows: Int = 10): Events
    suspend fun getEvent(id: String): Event
}