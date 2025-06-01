package denys.diomaxius.nzevents.data.remote.mapper

import denys.diomaxius.nzevents.data.remote.dto.EventDto
import denys.diomaxius.nzevents.data.remote.dto.EventsResponseDto
import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.domain.model.Events

fun EventsResponseDto.toDomain(): Events = Events(
    events = this.events.map { it.toDomain() }
)

fun EventDto.toDomain(): Event = Event(
    url = this.url,
    name = this.name,
    description = this.description,
    datetimeStart = this.datetimeStart,
    datetimeEnd = this.datetimeEnd,
    address = this.address,
    images = this.images.toDomain()
)