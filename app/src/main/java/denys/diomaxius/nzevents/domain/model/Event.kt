package denys.diomaxius.nzevents.domain.model

data class Event(
    val url: String,
    val name: String,
    val description: String,
    val datetimeStart: String,
    val datetimeEnd: String,
    val address: String,
    val images: ImageWrapper
)