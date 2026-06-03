package tech.tabrita.com.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit service for the local Python scraper API.
 * Run the server with:
 *   cd news-scraper
 *   python -m uvicorn scripts.api_server:app --host 0.0.0.0 --port 8000
 *
 * From Android emulator use base URL: http://10.0.2.2:8000/
 * (physical device: adb reverse tcp:8000 tcp:8000 then http://127.0.0.1:8000/)
 */
interface TaBritaApiService {

    @GET("articles")
    suspend fun getArticles(
        @Query("category") category: String? = null,
        @Query("limit") limit: Int = 50,
        @Query("page") page: Int = 1,
        @Query("q") query: String? = null
    ): ArticlesResponse

    @GET("articles/{id}")
    suspend fun getArticleById(
        @Path("id") id: String
    ): ArticleDto
}
