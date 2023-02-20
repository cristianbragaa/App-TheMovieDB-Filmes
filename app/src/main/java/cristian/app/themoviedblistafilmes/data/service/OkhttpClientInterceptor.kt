package cristian.app.themoviedblistafilmes.data.service

import okhttp3.OkHttpClient

class OkhttpClientInterceptor {

    companion object {
        fun getInstance(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor())
                .build()
        }
    }

}