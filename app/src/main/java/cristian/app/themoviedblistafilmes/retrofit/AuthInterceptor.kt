package cristian.app.themoviedblistafilmes.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        //Configurando API_KEY no QueryParameter
        val requisicaoAtual = chain.request().newBuilder()

        val urlAtual = chain.request().url()
        val novaUrl = urlAtual.newBuilder()
            .addQueryParameter("api_key", RetrofitInstance.API_KEY)
            .build()

        val novaRequisicao = requisicaoAtual.url(novaUrl)
        return chain.proceed(novaRequisicao.build())
    }
}