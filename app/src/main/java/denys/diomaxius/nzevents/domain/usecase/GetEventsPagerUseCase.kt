package denys.diomaxius.nzevents.domain.usecase

import androidx.paging.PagingData
import denys.diomaxius.nzevents.domain.model.Event
import denys.diomaxius.nzevents.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEventsPagerUseCase @Inject constructor(
    private val repository: EventsRepository
) {
    operator fun invoke(
        pageSize: Int = 100,
        startDate: String?,
        endDate: String?
    ): Flow<PagingData<Event>> =
        repository.getEventsPager(
            pageSize = pageSize,
            startDate = startDate,
            endDate = endDate
        )
}