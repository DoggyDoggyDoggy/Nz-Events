package denys.diomaxius.nzevents.domain.usecase

import denys.diomaxius.nzevents.domain.repository.EventsRepository
import javax.inject.Inject

class GetEventUseCase @Inject constructor(
    private val repository: EventsRepository
) {
    suspend operator fun invoke(id: Long) = repository.getEvent(id)
}