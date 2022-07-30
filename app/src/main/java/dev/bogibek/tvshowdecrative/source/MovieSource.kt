package dev.bogibek.tvshowdecrative.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.bogibek.tvshowdecrative.model.TVShow
import dev.bogibek.tvshowdecrative.repository.TVShowRepository
import retrofit2.HttpException
import java.io.IOException

class MovieSource(private val repository: TVShowRepository) : PagingSource<Int, TVShow>() {
    override fun getRefreshKey(state: PagingState<Int, TVShow>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShow> {
        return try {
            val nextPage = params.key ?: 1
            val movieList = repository.apiTVShowPopular(nextPage).body()!!
            val tvShows = movieList.tv_shows
            LoadResult.Page(
                data = tvShows,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (tvShows.isEmpty()) null else movieList.page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}