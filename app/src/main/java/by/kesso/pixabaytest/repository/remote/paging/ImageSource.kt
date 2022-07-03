package by.kesso.pixabaytest.repository.remote.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import by.kesso.pixabaytest.domain.entity.PixaImage
import by.kesso.pixabaytest.repository.remote.client.GetImages
import by.kesso.pixabaytest.repository.remote.mapper.Mapper
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ImageSource(
    private val key: String,
    private val service: GetImages,
) : RxPagingSource<Int, PixaImage>() {

    override fun getRefreshKey(state: PagingState<Int, PixaImage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, PixaImage>> {
        val page = params.key ?: DEFAULT_PAGE_INDEX

        return service.get(key, page + 1, params.loadSize)
            .subscribeOn(Schedulers.io())
            .map { Mapper.transform(it) }
            .map { it.toLoadResult(page) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun List<PixaImage>.toLoadResult(page: Int): LoadResult<Int, PixaImage> {
        return LoadResult.Page(
            this, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
            nextKey = if (isEmpty()) null else page + 1
        )
    }

    companion object {
        const val DEFAULT_PAGE_INDEX = 0
    }
}