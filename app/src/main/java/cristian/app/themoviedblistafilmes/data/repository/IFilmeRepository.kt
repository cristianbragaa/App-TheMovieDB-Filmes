package cristian.app.themoviedblistafilmes.data.repository

import cristian.app.themoviedblistafilmes.domain.model.Detalhes
import cristian.app.themoviedblistafilmes.domain.model.Filme
import cristian.app.themoviedblistafilmes.domain.model.Similar

interface IFilmeRepository {
    suspend fun recuperarFilmesPopulares(): List<Filme>
    suspend fun recuperarFilmesPesquisa(pesquisaDigitada: String): List<Filme>
    suspend fun recuperarFilmeDetalhes(movieId: Int): Detalhes
    suspend fun recuperandoListaFilmesSimilares(movieId: Int): List<Similar>

}