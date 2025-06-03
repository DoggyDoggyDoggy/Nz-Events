package denys.diomaxius.nzevents.data.remote.api

import denys.diomaxius.nzevents.data.remote.dto.EventsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface EventsFindApi  {
    @GET("events.json")
    suspend fun getEvents(
        @Query("rows") rows: Int
    ): EventsResponseDto

    @GET("events.json")
    suspend fun getEvent(
        @Query("id") id: String
    ): EventsResponseDto

    @GET("events.json")
    suspend fun getEventsByLocation(
        @Query("location") location: Int
    ): EventsResponseDto
}