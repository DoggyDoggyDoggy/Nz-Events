package denys.diomaxius.nzevents.data.remote.api

import denys.diomaxius.nzevents.data.remote.dto.EventDto
import denys.diomaxius.nzevents.data.remote.dto.EventsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EventsFindApi  {
    @GET("events.json")
    suspend fun getEvents(
        @Query("rows") rows: Int = 10
    ): EventsResponseDto

    @GET("events.json")
    suspend fun getEvent(
        @Query("id") id: Long
    ): EventDto
}