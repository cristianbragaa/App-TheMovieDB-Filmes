package cristian.app.themoviedblistafilmes.data.repository

import cristian.app.themoviedblistafilmes.data.model.popular.toFilme
import cristian.app.themoviedblistafilmes.data.service.FilmeAPI
import cristian.app.themoviedblistafilmes.domain.model.Filme
import java.io.File
import javax.inject.Inject

/*
* Classe responsável por: Receber os dados do service (API),
*                         usar map para transformar os objetos e
*                         mandar para FilmeUseCase.
* */

class FilmeRepository @Inject constructor(
    private val service: FilmeAPI
) : IFilmeRepository {

    override suspend fun recuperarFilmesPopulares(): List<Filme> {
        val response = service.getPopularMovies()
        if (response.isSuccessful) {
            response.body()?.let { filmeResponse ->
                val listaFilmeDTO = filmeResponse.listaFilmes
                val listaFilmes = listaFilmeDTO.map { it.toFilme() }

                return listaFilmes
            }
        }
        return emptyList()
    }
}