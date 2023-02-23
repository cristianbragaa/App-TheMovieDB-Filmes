package cristian.app.themoviedblistafilmes.data.repository

import cristian.app.themoviedblistafilmes.domain.model.Filme

interface IFilmeRepository {
    suspend fun recuperarFilmesPopulares(): List<Filme>
    suspend fun recuperarFilmesPesquisa(pesquisaDigitada: String): List<Filme>
}