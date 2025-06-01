package denys.diomaxius.nzevents.domain.model

data class Image(
    val id: Long,
    val isPrimary: Boolean,
    val originalUrl: String,
    val transforms: TransformWrapper
)
