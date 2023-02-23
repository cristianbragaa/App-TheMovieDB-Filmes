package cristian.app.themoviedblistafilmes.domain.usecase

import cristian.app.themoviedblistafilmes.data.repository.IFilmeRepository
import cristian.app.themoviedblistafilmes.domain.model.Filme
import cristian.app.themoviedblistafilmes.domain.model.toFilmeUI
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class FilmeUseCase @Inject constructor(
    private val iFilmeRepository: IFilmeRepository
) : IFilmeUseCase {

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

            val listaFilmeUI = listaImagensNaoNulas.map {
                it.toFilmeUI()
            }
            return listaFilmeUI
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }


}