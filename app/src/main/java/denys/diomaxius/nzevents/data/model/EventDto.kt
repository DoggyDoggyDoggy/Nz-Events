package denys.diomaxius.nzevents.data.model

import com.google.gson.annotations.SerializedName


data class EventsResponseDto(
    val events: List<EventDto>
)

data class EventDto(
    val url: String,
    val name: String,
    val description: String,
    @SerializedName("datetime_start") val datetimeStart: String,
    @SerializedName("datetime_end") val datetimeEnd: String,
    val address: String,
    val images: ImageWrapperDto
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