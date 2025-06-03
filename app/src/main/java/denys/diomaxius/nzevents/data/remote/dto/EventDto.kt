package denys.diomaxius.nzevents.data.remote.dto

import com.google.gson.annotations.SerializedName


data class EventsResponseDto(
    val events: List<EventDto>
)

data class EventDto(
    val id: String,
    val url: String,
    val name: String,
    val description: String,
    @SerializedName("datetime_start") val datetimeStart: String,
    @SerializedName("datetime_end") val datetimeEnd: String,
    val address: String,
    val sessions: SessionsWrapperDto,
    val images: ImageWrapperDto
)

data class SessionsWrapperDto(
    val sessions: List<SessionDto>
)

data class SessionDto(
    @SerializedName("datetime_summary") val datetimeSummary: String
)

data class ImageWrapperDto(
    val images: List<ImageDto>
)

data class ImageDto(
    val id: Long,
    @SerializedName("is_primary") val isPrimary: Boolean,
    @SerializedName("original_url") val originalUrl: String,
    val transforms: TransformWrapperDto
)

data class TransformWrapperDto(
    val transforms: List<ImageTransformDto>
)

data class ImageTransformDto(
    @SerializedName("transformation_id") val transformationId: Int,
    val url: String,
    val width: Int,
    val height: Int
)