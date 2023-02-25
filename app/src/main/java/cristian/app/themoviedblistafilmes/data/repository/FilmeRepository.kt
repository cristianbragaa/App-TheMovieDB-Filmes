package cristian.app.themoviedblistafilmes.data.repository

import android.util.Log
import cristian.app.themoviedblistafilmes.data.model.detail.toDetalhes
import cristian.app.themoviedblistafilmes.data.model.popular.toFilme
import cristian.app.themoviedblistafilmes.data.service.FilmeAPI
import cristian.app.themoviedblistafilmes.domain.model.Detalhes
import cristian.app.themoviedblistafilmes.domain.model.Filme
import javax.inject.Inject

class FilmeRepository @Inject constructor(
    private val service: FilmeAPI
) : IFilmeRepository {
    //Primeira tela
    override suspend fun recuperarFilmesPopulares(): List<Filme> {
        val response = service.getPopularMovies()

        if (response.isSuccessful) {
            response.body()?.let { filmeResponse ->
                val listaFilmeDTO = filmeResponse.listaFilmes
                val listaFilme = listaFilmeDTO.map { it.toFilme() }
                return listaFilme
            }
        }
        return emptyList()
    }

    override suspend fun recuperarFilmesPesquisa(pesquisaDigitada: String): List<Filme> {
        val response = service.getSearchMovie(
            pesquisaDigitada
        )
        if (response.isSuccessful) {
            response.body()?.let { filmeResponse ->
                val listaFilmeDTO = filmeResponse.listaFilmes
                val listaFilme = listaFilmeDTO.map { it.toFilme() }
                return listaFilme
            }
        }
        return emptyList()
    }

    //Segunda tela
    override suspend fun recuperarFilmeDetalhes(movie_id: Int): Detalhes {
        val response = service.getDetailsMovies(movie_id)

        if (response.isSuccessful) {
            response.body()?.let { detalhesDTO ->
                val detalhes = detalhesDTO.toDetalhes()
                return detalhes
            }
        }
        throw Exception("Erro ao recuperar detalhes do filme")
    }

    override suspend fun recuperandoListaFilmesSimilares(movie_id: Int): List<Filme> {
        val response = service.getSimilarMovies(movie_id)

        if (response.isSuccessful) {
            response.body()?.let { filmeResponse ->
                val listaFilmeDTO = filmeResponse.listaFilmes
                val listaFilme = listaFilmeDTO.map {
                    it.toFilme()
                }
                return listaFilme
            }
        }
        return emptyList()
    }


}