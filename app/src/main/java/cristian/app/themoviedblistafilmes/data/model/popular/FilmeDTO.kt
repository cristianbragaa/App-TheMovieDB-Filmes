package cristian.app.themoviedblistafilmes.data.model.popular

import com.google.gson.annotations.SerializedName
import cristian.app.themoviedblistafilmes.domain.model.Detalhes
import cristian.app.themoviedblistafilmes.domain.model.Filme

data class FilmeDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("release_date")
    val dataLancamento: String,
    @SerializedName("poster_path")
    val imagem: String?,
    @SerializedName("title")
    val titulo: String,
    @SerializedName("popularity")
    var popularidade: Double,
    @SerializedName("vote_count")
    val qtdVotos: Double,
    @SerializedName("overview")
    val resumoFilme: String,
    @SerializedName("genre_ids")
    val generos: List<Int>
)

fun FilmeDTO.toFilme(): Filme {
    return Filme(
        id = this.id,
        dataLancamento = this.dataLancamento,
        imagem = this.imagem,
        titulo = this.titulo,
        popularidade = this.popularidade,
        qtdVotos = this.qtdVotos,
        resumoFilme = this.resumoFilme,
        generos = this.generos
    )
}


