package cristian.app.themoviedblistafilmes.domain.usecase

import cristian.app.themoviedblistafilmes.data.repository.IFilmeRepository
import cristian.app.themoviedblistafilmes.domain.model.*
import cristian.app.themoviedblistafilmes.presentation.model.DetalhesUI
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI
import cristian.app.themoviedblistafilmes.presentation.model.SimilarUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class FilmeUseCase @Inject constructor(
    private val iFilmeRepository: IFilmeRepository
) : IFilmeUseCase {

    override suspend fun recuperarFilmesPopulares(): List<FilmeUI> {
        return try {
            val filmes = iFilmeRepository.recuperarFilmesPopulares()

            val listaFilmeUI = filmes.map { it.toFilmeUI() }
            return listaFilmeUI
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun recuperarFilmesPesquisa(pesquisaDigitada: String): List<FilmeUI> {
        return try {
            val filmes = iFilmeRepository.recuperarFilmesPesquisa(pesquisaDigitada)

            val listaFilmePesquisa = mutableListOf<Filme>()
            filmes.forEach { filme ->
                if (filme.imagem != null) {
                    listaFilmePesquisa.add(filme)
                }
            }
            val listaFilmeUI = listaFilmePesquisa.map { it.toFilmeUI() }
            listaFilmeUI
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun recuperarFilmeDetalhes(movieId: Int): DetalhesUI {
        try {
            val detalhes = iFilmeRepository.recuperarFilmeDetalhes(movieId)

            val detalhesUI = detalhes.toDetalhesUI()
            return detalhesUI
        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("Erro ao recuperar detalhes do filme")
        }
    }

    override suspend fun recuperandoListaFilmesSimilares(movieId: Int): List<SimilarUI> {
        return try {
            val similar = iFilmeRepository.recuperandoListaFilmesSimilares(movieId)

            val listaFilmeSimilar = mutableListOf<Similar>()
            similar.forEach { similar ->
                if (similar.imagem != null && similar.imagem != "") {
                    listaFilmeSimilar.add(similar)
                }
            }
            val listaSimilares = listaFilmeSimilar.map { it.toSimilarUI() }
            listaSimilares
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }

    }
}