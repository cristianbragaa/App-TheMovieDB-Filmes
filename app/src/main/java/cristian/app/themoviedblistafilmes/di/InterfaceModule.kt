package cristian.app.themoviedblistafilmes.di

import cristian.app.themoviedblistafilmes.data.repository.FilmeRepository
import cristian.app.themoviedblistafilmes.data.repository.IFilmeRepository
import cristian.app.themoviedblistafilmes.domain.usecase.FilmeUseCase
import cristian.app.themoviedblistafilmes.domain.usecase.IFilmeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class InterfaceModule {

    /*
    * A anotação @Binds informa ao Hilt qual implementação usar quando
    * é necessário fornecer uma instância de uma interface.
    * */

    @Binds
    abstract fun bindInterfaceUseCase(
        filmeUseCase: FilmeUseCase
    ): IFilmeUseCase

    @Binds
    abstract fun bindInterfaceRepository(
        filmeRepository: FilmeRepository
    ): IFilmeRepository

}