package cristian.app.themoviedblistafilmes.domain.usecase

import cristian.app.themoviedblistafilmes.domain.model.Filme
import cristian.app.themoviedblistafilmes.presentation.model.DetalhesUI
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI

interface IFilmeUseCase {
    suspend fun recuperarFilmesPopulares(): List<FilmeUI>
    suspend fun recuperarFilmesPesquisa(pesquisaDigitada: String): List<FilmeUI>
    suspend fun recuperarFilmeDetalhes(movie_id: Int): DetalhesUI
    suspend fun recuperandoListaFilmesSimilares(movie_id: Int): List<FilmeUI>

}