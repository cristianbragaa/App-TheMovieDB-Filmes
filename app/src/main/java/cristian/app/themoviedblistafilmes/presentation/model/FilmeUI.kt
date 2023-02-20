package cristian.app.themoviedblistafilmes.presentation.model

import com.google.gson.annotations.SerializedName

data class FilmeUI(
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
