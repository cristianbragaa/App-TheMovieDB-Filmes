package cristian.app.themoviedblistafilmes.domain.usecase

import cristian.app.themoviedblistafilmes.data.repository.IFilmeRepository
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI
import javax.inject.Inject

class FilmeUseCase @Inject constructor(
    private val iFilmeRepository: IFilmeRepository
) : IFilmeUseCase {

    override suspend fun recuperarFilmesPopulares(): List<FilmeUI> {
        try {
            iFilmeRepository.recuperarFilmesPopulares()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }

}