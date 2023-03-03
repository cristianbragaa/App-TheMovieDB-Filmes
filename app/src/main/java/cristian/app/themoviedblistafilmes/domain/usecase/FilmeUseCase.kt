package cristian.app.themoviedblistafilmes.domain.usecase

import cristian.app.themoviedblistafilmes.data.repository.IFilmeRepository
import cristian.app.themoviedblistafilmes.domain.model.Filme
import cristian.app.themoviedblistafilmes.domain.model.toDetalhesUI
import cristian.app.themoviedblistafilmes.domain.model.toFilmeUI
import cristian.app.themoviedblistafilmes.domain.model.toSimilarUI
import cristian.app.themoviedblistafilmes.presentation.model.DetalhesUI
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI
import cristian.app.themoviedblistafilmes.presentation.model.SimilarUI
import javax.inject.Inject

class FilmeUseCase @Inject constructor(
    private val iFilmeRepository: IFilmeRepository
) : IFilmeUseCase {

    //Primeira tela
    override suspend fun recuperarFilmesPopulares(): List<FilmeUI> {
        return try {
            val listaFilme = iFilmeRepository.recuperarFilmesPopulares()

            val listaFilmeUI = listaFilme.map { it.toFilmeUI() }
            return listaFilmeUI
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun recuperarFilmesPesquisa(pesquisaDigitada: String): List<FilmeUI> {
        return try {
            val listaFilme = iFilmeRepository.recuperarFilmesPesquisa(pesquisaDigitada)

            val listaImagensNaoNulas = mutableListOf<Filme>()
            listaFilme.forEach { filme ->
                if (filme.imagem != null) {
                    listaImagensNaoNulas.add(filme)
                }
            }
            val listaFilmeUI = listaImagensNaoNulas.map { it.toFilmeUI() }
            listaFilmeUI
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    //Segunda tela
    override suspend fun recuperarFilmeDetalhes(movie_id: Int): DetalhesUI {
        try {
            val detalhes = iFilmeRepository.recuperarFilmeDetalhes(movie_id)

            val detalhesUI = detalhes.toDetalhesUI()
            return detalhesUI
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("Erro ao recuperar detalhes do filme")
        }
    }

    override suspend fun recuperandoListaFilmesSimilares(movie_id: Int): List<SimilarUI> {
        return try {
            val listaFilme = iFilmeRepository.recuperandoListaFilmesSimilares(movie_id)

            /*val listaImagensNaoNulas = mutableListOf<Filme>()
            listaFilme.forEach {
                if (it.imagem != null) {
                    listaImagensNaoNulas.add(it)
                }
            }*/
            val listaSimilares = listaFilme.map {
                it.toSimilarUI()
            }
            listaSimilares
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }

    }
}