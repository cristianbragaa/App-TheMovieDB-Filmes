package cristian.app.themoviedblistafilmes.data.model.detail

import com.google.gson.annotations.SerializedName
import cristian.app.themoviedblistafilmes.domain.model.Detalhes

data class DetalhesDTO(
    @SerializedName("poster_path")
    var imagem: String?,
    @SerializedName("genres")
    var generos: List<DetalhesGenero>,
    @SerializedName("overview")
    var resumo: String,
    @SerializedName("popularity")
    var popularidade: Double,
    @SerializedName("release_date")
    var dataLancamento: String?,
    @SerializedName("title")
    var titulo: String,
    @SerializedName("vote_count")
    var qtdVotos: Int
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