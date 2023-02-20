package cristian.app.themoviedblistafilmes.di

import cristian.app.themoviedblistafilmes.helper.Constants
import cristian.app.themoviedblistafilmes.data.service.FilmeAPI
import cristian.app.themoviedblistafilmes.data.service.OkhttpClientInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun proverRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkhttpClientInterceptor.getInstance())
            .build()
    }

    @Singleton
    @Provides
    fun proverFilmeApi(retrofit: Retrofit): FilmeAPI {
        return retrofit.create(FilmeAPI::class.java)
    }

}


