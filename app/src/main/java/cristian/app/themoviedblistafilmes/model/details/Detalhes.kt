package cristian.app.themoviedblistafilmes.model.details

import com.google.gson.annotations.SerializedName

data class Detalhes(
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