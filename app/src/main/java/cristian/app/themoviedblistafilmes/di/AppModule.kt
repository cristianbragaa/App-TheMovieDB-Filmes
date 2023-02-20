package cristian.app.themoviedblistafilmes.di

import cristian.app.themoviedblistafilmes.constants.Constants
import cristian.app.themoviedblistafilmes.data.repository.FilmeRepository
import cristian.app.themoviedblistafilmes.data.repository.IFilmeRepository
import cristian.app.themoviedblistafilmes.data.service.FilmeAPI
import cristian.app.themoviedblistafilmes.data.service.OkhttpClientInterceptor
import cristian.app.themoviedblistafilmes.domain.usecase.FilmeUseCase
import cristian.app.themoviedblistafilmes.domain.usecase.IFilmeUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun proverFilmeApi(): FilmeAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkhttpClientInterceptor.getInstance())
            .build()
            .create(FilmeAPI::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class InterfaceModule {

    @Binds
    abstract fun bindIFilmeRepository(
        iFilmeRepository: IFilmeRepository
    ): IFilmeUseCase

}