package denys.diomaxius.nzevents.data.remote.mapper

import denys.diomaxius.nzevents.data.remote.dto.ImageDto
import denys.diomaxius.nzevents.data.remote.dto.ImageTransformDto
import denys.diomaxius.nzevents.data.remote.dto.ImageWrapperDto
import denys.diomaxius.nzevents.data.remote.dto.TransformWrapperDto
import denys.diomaxius.nzevents.domain.model.Image
import denys.diomaxius.nzevents.domain.model.ImageTransform
import denys.diomaxius.nzevents.domain.model.ImageWrapper
import denys.diomaxius.nzevents.domain.model.TransformWrapper

fun ImageWrapperDto.toDomain(): ImageWrapper = ImageWrapper(
    images = this.images.map { it.toDomain()  }
)

fun ImageDto.toDomain() : Image = Image(
    id = this.id,
    isPrimary = this.isPrimary,
    originalUrl = this.originalUrl,
    transforms = this.transforms.toDomain()
)

fun TransformWrapperDto.toDomain(): TransformWrapper = TransformWrapper(
    transforms = this.transforms.map { it.toDomain() }
)

fun ImageTransformDto.toDomain(): ImageTransform = ImageTransform(
    transformationId = this.transformationId,
    url = this.url,
    width = this.width,
    height = this.height
)