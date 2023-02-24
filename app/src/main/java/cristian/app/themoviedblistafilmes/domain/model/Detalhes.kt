package cristian.app.themoviedblistafilmes.domain.model

import com.google.gson.annotations.SerializedName
import cristian.app.themoviedblistafilmes.data.model.detail.DetalhesDTO
import cristian.app.themoviedblistafilmes.data.model.detail.DetalhesGenero
import cristian.app.themoviedblistafilmes.presentation.model.DetalhesUI

data class Detalhes (
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
