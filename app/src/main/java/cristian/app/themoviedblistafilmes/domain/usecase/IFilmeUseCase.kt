package cristian.app.themoviedblistafilmes.domain.usecase

import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI

interface IFilmeUseCase {
    suspend fun recuperarFilmesPopulares(): List<FilmeUI>
    suspend fun recuperarFilmesPesquisa(pesquisaDigitada: String): List<FilmeUI>

}