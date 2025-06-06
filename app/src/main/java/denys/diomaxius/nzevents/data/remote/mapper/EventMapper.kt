package denys.diomaxius.nzevents.data.remote.mapper

import denys.diomaxius.nzevents.data.remote.dto.EventDto
import denys.diomaxius.nzevents.data.remote.dto.EventsResponseDto
import denys.diomaxius.nzevents.data.remote.dto.SessionDto
import denys.diomaxius.nzevents.data.remote.dto.SessionsWrapperDto
import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.domain.model.Events
import denys.diomaxius.nzevents.domain.model.Session
import denys.diomaxius.nzevents.domain.model.SessionsWrapper

fun EventsResponseDto.toDomain(): Events = Events(
    count = this.attributes.count,
    events = this.events.map { it.toDomain() }
)

fun EventDto.toDomain(): Event = Event(
    id = this.id,
    url = this.url,
    name = this.name,
    description = this.description,
    datetimeStart = this.datetimeStart,
    datetimeEnd = this.datetimeEnd,
    address = this.address,
    sessions = this.sessions.toDomain(),
    images = this.images.toDomain()
)

fun SessionsWrapperDto.toDomain() : SessionsWrapper = SessionsWrapper(
    sessions = this.sessions.map { it.toDomain() }
)

fun SessionDto.toDomain() : Session = Session(
    datetimeSummary = this.datetimeSummary
)