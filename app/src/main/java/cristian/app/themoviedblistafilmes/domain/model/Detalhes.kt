package cristian.app.themoviedblistafilmes.domain.model

import com.google.gson.annotations.SerializedName
import cristian.app.themoviedblistafilmes.data.model.detail.DetalhesDTO
import cristian.app.themoviedblistafilmes.data.model.detail.DetalhesGenero
import cristian.app.themoviedblistafilmes.presentation.model.DetalhesUI

data class Detalhes (
    @SerializedName("poster_path")
    val imagem: String,
    @SerializedName("genres")
    val generos: List<DetalhesGenero>,
    @SerializedName("overview")
    val resumo: String,
    @SerializedName("popularity")
    val popularidade: Double,
    @SerializedName("release_date")
    val dataLancamento: String,
    @SerializedName("title")
    val titulo: String,
    @SerializedName("vote_count")
    val qtdVotos: Int
)

fun Detalhes.toDetalhesUI() : DetalhesUI {
    return DetalhesUI(
        imagem = this.imagem,
        generos = this.generos,
        resumo = this.resumo,
        popularidade = this.popularidade,
        dataLancamento = this.dataLancamento,
        titulo = this.titulo,
        qtdVotos = this.qtdVotos
    )
}
