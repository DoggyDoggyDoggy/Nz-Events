package denys.diomaxius.nzevents.domain.repository

import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.domain.model.Events

interface EventsRepository {
    suspend fun getEvents(rows: Int): Events
    suspend fun getEvent(id: String): Event
    suspend fun getEventsByLocation(location: Int): Events
}