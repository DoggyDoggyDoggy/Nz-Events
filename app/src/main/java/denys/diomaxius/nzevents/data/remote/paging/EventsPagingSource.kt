package denys.diomaxius.nzevents.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import denys.diomaxius.nzevents.data.remote.api.EventsFindApi
import denys.diomaxius.nzevents.data.remote.mapper.toDomain
import denys.diomaxius.nzevents.domain.model.Event

class EventsPagingSource(
    private val api: EventsFindApi,
    private val locationId: Int? = null,
    private val pageSize: Int = 100
) : PagingSource<Int, Event>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Event> {
        return try {
            val offset = params.key ?: 0

            val response = if (locationId == null) {
                api.getEvents(rows = pageSize, offset = offset)
            } else {
                api.getEventsByLocation(location = locationId, rows = pageSize, offset = offset)
            }

            val items: List<Event> = response.events.map { dto ->
                dto.toDomain()
            }

            val nextOffset = if (items.isEmpty() || offset + items.size >= response.attributes.count) {
                null
            } else {
                offset + items.size
            }

            LoadResult.Page(
                data = items,
                prevKey = null,
                nextKey = nextOffset
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Event>): Int? {
        return state.anchorPosition?.let { anchor ->
            val page = state.closestPageToPosition(anchor)
            page?.prevKey ?: page?.nextKey?.minus(pageSize)
        }
    }
}