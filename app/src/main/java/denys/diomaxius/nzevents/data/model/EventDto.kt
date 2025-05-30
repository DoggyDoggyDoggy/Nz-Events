package denys.diomaxius.nzevents.data.model

import com.google.gson.annotations.SerializedName

data class EventDto(
    val url: String,
    val name: String,
    val description: String?,
    @SerializedName("datetime_start") val datetimeStart: String?,
    @SerializedName("datetime_end") val datetimeEnd: String?,
    val address: String?
)
