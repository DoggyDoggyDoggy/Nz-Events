package denys.diomaxius.nzevents.data.network

import denys.diomaxius.nzevents.data.model.EventsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EventFindApi {
    @GET("events.json")
    suspend fun getEvents(
        @Query("rows") rows: Int = 10
    ): EventsResponseDto
}