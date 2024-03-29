package cristian.app.themoviedblistafilmes.data.service

import cristian.app.themoviedblistafilmes.data.model.detail.DetalhesDTO
import cristian.app.themoviedblistafilmes.data.model.popular.FilmeResponse
import cristian.app.themoviedblistafilmes.data.model.similar.SimilarResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmeAPI {
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<FilmeResponse>

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("query") pesquisa: String
    ): Response<FilmeResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetailsMovies(
        @Path("movie_id") movieId: Int
    ): Response<DetalhesDTO>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movie_id: Int
    ): Response<SimilarResponse>
}