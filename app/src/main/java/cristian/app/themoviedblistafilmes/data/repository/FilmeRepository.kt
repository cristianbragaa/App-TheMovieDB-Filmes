package cristian.app.themoviedblistafilmes.data.repository

import cristian.app.themoviedblistafilmes.data.service.FilmeAPI
import cristian.app.themoviedblistafilmes.domain.model.Filme
import javax.inject.Inject

class FilmeRepository @Inject constructor(
    private val service: FilmeAPI
) : IFilmeRepository {

    override suspend fun recuperarFilmesPopulares(): List<Filme> {
        TODO("Not yet implemented")
    }

}