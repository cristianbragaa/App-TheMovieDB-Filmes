package cristian.app.themoviedblistafilmes.data.repository

import cristian.app.themoviedblistafilmes.data.model.detail.DetalhesDTO
import cristian.app.themoviedblistafilmes.domain.model.Detalhes
import cristian.app.themoviedblistafilmes.domain.model.Filme

interface IFilmeRepository {
    suspend fun recuperarFilmesPopulares(): List<Filme>
    suspend fun recuperarFilmesPesquisa(pesquisaDigitada: String): List<Filme>
    suspend fun recuperarFilmeDetalhes(movie_id: Int): Detalhes
}