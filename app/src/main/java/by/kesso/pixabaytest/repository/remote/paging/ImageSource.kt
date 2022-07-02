package by.kesso.pixabaytest.repository.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import by.kesso.pixabaytest.domain.entity.PixaImage
import by.kesso.pixabaytest.repository.remote.client.GetImages
import by.kesso.pixabaytest.repository.remote.mapper.Mapper
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class ImageSource(
    private val key: String,
    private val service: GetImages,
) : PagingSource<Int, PixaImage>() {

    override fun getRefreshKey(state: PagingState<Int, PixaImage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PixaImage> {
        val page = params.key ?: DEFAULT_PAGE_INDEX

        return try {
            val response = service.get(key, page + 1, params.loadSize)
                .subscribeOn(Schedulers.io())
                .map { response ->
                    Timber.d(response.totalHits.toString())
                    response.hits?.mapNotNull {
                        it?.let { Mapper.map(it) }
                    }?: emptyList()
                }
                .onErrorReturn {
                    Timber.e(it)
                    emptyList()
                }.blockingGet()

            LoadResult.Page(
                response, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        const val DEFAULT_PAGE_INDEX = 0
    }
}