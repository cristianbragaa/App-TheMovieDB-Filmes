package cristian.app.themoviedblistafilmes.domain.model

import com.google.gson.annotations.SerializedName
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI

data class Filme(
    @SerializedName("id")
    val id: Int,
    @SerializedName("release_date")
    val dataLancamento: String,
    @SerializedName("poster_path")
    val imagem: String?,
    @SerializedName("title")
    val titulo: String,
    @SerializedName("popularity")
    val popularidade: Double,
    @SerializedName("vote_count")
    val qtdVotos: Double,
    @SerializedName("overview")
    val resumoFilme: String,
    @SerializedName("genre_ids")
    val generos: List<Int>
)

fun Filme.toFilmeUI(): FilmeUI {
    return FilmeUI(
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