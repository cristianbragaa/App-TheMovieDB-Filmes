package cristian.app.themoviedblistafilmes.domain.usecase

import cristian.app.themoviedblistafilmes.data.repository.IFilmeRepository
import cristian.app.themoviedblistafilmes.domain.model.Filme
import cristian.app.themoviedblistafilmes.domain.model.toDetalhesUI
import cristian.app.themoviedblistafilmes.domain.model.toFilmeUI
import cristian.app.themoviedblistafilmes.presentation.model.DetalhesUI
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI
import javax.inject.Inject

class FilmeUseCase @Inject constructor(
    private val iFilmeRepository: IFilmeRepository
) : IFilmeUseCase {

    //Primeira tela
    override suspend fun recuperarFilmesPopulares(): List<FilmeUI> {
        try {
            val listaFilmes = iFilmeRepository.recuperarFilmesPopulares()

            val listaFilmeUI = listaFilmes.map { it.toFilmeUI() }
            return listaFilmeUI
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }

    override suspend fun recuperarFilmesPesquisa(pesquisaDigitada: String): List<FilmeUI> {
        try {
            val listaFilmes = iFilmeRepository.recuperarFilmesPesquisa(pesquisaDigitada)

            val listaImagensNaoNulas = mutableListOf<Filme>()
            listaFilmes.forEach {
                if (it.imagem != null) {
                    listaImagensNaoNulas.add(it)
                }
            }
            val listaFilmeUI = listaImagensNaoNulas.map { it.toFilmeUI() }
            return listaFilmeUI
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }

    //Segunda tela
    override suspend fun recuperarFilmeDetalhes(movie_id: Int): DetalhesUI {
        try {
            val detalhes = iFilmeRepository.recuperarFilmeDetalhes(movie_id)

            val detalhesUI = detalhes.toDetalhesUI()
            return detalhesUI
        } catch (e: Exception) {
            e.printStackTrace()
        }
        throw Exception("Erro ao recuperar detalhes do filme")
    }

    override suspend fun recuperandoListaFilmesSimilares(movie_id: Int): List<FilmeUI> {
        try {
            val listaFilmes = iFilmeRepository.recuperandoListaFilmesSimilares(movie_id)

            val listaFilmeUI = listaFilmes.map { it.toFilmeUI() }
            return if (listaFilmeUI.isNotEmpty()){
                listaFilmeUI
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }
}