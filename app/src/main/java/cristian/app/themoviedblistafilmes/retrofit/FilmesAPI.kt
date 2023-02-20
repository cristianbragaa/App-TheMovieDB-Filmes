package cristian.app.themoviedblistafilmes.retrofit

import cristian.app.themoviedblistafilmes.model.details.Detalhes
import cristian.app.themoviedblistafilmes.model.genres.GeneroLista
import cristian.app.themoviedblistafilmes.model.popular.FilmesResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmesAPI {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<FilmesResult>

    @GET("movie/{movie_id}")
    suspend fun getDetailsMovies(
        @Path("movie_id") movie_id: Int
    ): Response<Detalhes>

    @GET("genre/movie/list")
    suspend fun getListGenres(): Response<GeneroLista>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movie_id: Int
    ): Response<FilmesResult>

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("query") pesquisa: String
    ): Response<FilmesResult>

}