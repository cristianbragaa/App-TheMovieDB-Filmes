package cristian.app.themoviedblistafilmes.data.model.detail

import com.google.gson.annotations.SerializedName
import cristian.app.themoviedblistafilmes.domain.model.Detalhes

data class DetalhesDTO(
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
fun DetalhesDTO.toDetalhes() : Detalhes {
    return Detalhes(
        imagem = this.imagem,
        generos = this.generos,
        resumo = this.resumo,
        popularidade = this.popularidade,
        dataLancamento = this.dataLancamento,
        titulo = this.titulo,
        qtdVotos = this.qtdVotos
    )
}