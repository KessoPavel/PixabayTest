package by.kesso.pixabaytest.repository.remote.mapper

import by.kesso.pixabaytest.domain.entity.PixaImage
import by.kesso.pixabaytest.repository.remote.entity.ImagePage
import by.kesso.pixabaytest.repository.remote.entity.RemoteImage

object Mapper {
    private fun map(image: RemoteImage): PixaImage {
        return PixaImage(
            id = image.id?:0L,
            type = image.type,
            largeImageURL = image.largeImageURL,
            imageHeight = image.imageHeight,
            imageSize = image.imageSize,
            imageWidth = image.imageWidth,
            webFormatURL = image.webFormatURL,
            webFormatHeight = image.webFormatHeight,
            webFormatWidth = image.webFormatWidth,
            previewURL = image.previewURL,
            previewWidth = image.previewWidth,
            previewHeight = image.previewHeight,
            pageURL = image.pageURL,
            likes = image.likes,
            views = image.views,
            downloads = image.downloads,
            comments = image.comments,
            tags = image.tags,
            collections = image.collections,
            user = image.user,
            userId = image.userId,
            userImageURL = image.userImageURL,
        )
    }

    fun transform(imagePage: ImagePage): List<PixaImage> {
        return imagePage.hits?.mapNotNull { it?.let { map(it) } }?: emptyList()
    }
}