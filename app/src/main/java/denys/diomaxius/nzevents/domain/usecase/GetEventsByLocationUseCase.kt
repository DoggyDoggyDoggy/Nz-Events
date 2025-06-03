package denys.diomaxius.nzevents.domain.usecase

import denys.diomaxius.nzevents.domain.model.Events
import denys.diomaxius.nzevents.domain.repository.EventsRepository
import javax.inject.Inject

class GetEventsByLocationUseCase @Inject constructor(
    private val repository: EventsRepository
) {
    suspend operator fun invoke(location: Int): Events =
        repository.getEventsByLocation(location)
}