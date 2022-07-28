package dev.bogibek.tvshowdecrative.repository

import dev.bogibek.tvshowdecrative.data.db.TVShowDao
import dev.bogibek.tvshowdecrative.data.network.TVSHowService
import dev.bogibek.tvshowdecrative.model.TVShow
import javax.inject.Inject

class TVShowRepository @Inject constructor(
    private val tvshowService: TVSHowService,
    private val tvShowDao: TVShowDao
) {

    suspend fun apiTVShowPopular(page: Int) = tvshowService.apiTVShowPopular(page)
    suspend fun apiTVShowDetails(q: Int) = tvshowService.apiTVShowDetails(q)

    suspend fun getTVShowFromDB() = tvShowDao.getTVShowsFromDB()
    suspend fun insertTVShowToDB(tvShow: TVShow) = tvShowDao.insertTVShowToDB(tvShow)
    suspend fun deleteTVShowFromDB() = tvShowDao.deleteTvShowsFromDB()

}