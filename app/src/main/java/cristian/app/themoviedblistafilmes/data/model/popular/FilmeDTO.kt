package cristian.app.themoviedblistafilmes.data.model.popular

import com.google.gson.annotations.SerializedName
import cristian.app.themoviedblistafilmes.domain.model.Filme

data class FilmeDTO(
    @SerializedName("id")
    var id: Int,
    @SerializedName("release_date")
    var dataLancamento: String?,
    @SerializedName("poster_path")
    var imagem: String?,
    @SerializedName("title")
    var titulo: String,
    @SerializedName("popularity")
    var popularidade: Double,
    @SerializedName("vote_count")
    var qtdVotos: Double,
    @SerializedName("overview")
    var resumoFilme: String,
    @SerializedName("genre_ids")
    var generos: List<Int>
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
