package denys.diomaxius.nzevents.domain.model

data class ImageTransform(
    val transformationId: Int,
    val url: String,
    val width: Int,
    val height: Int
)
