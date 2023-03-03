package cristian.app.themoviedblistafilmes.data.model.similar

import com.google.gson.annotations.SerializedName
import cristian.app.themoviedblistafilmes.domain.model.Similar

data class SimilarDTO(
    @SerializedName("poster_path")
    val imagem: String,
    @SerializedName("title")
    val titulo: String,
    @SerializedName("release_date")
    val dataLancamento: String
)

fun SimilarDTO.toSimilar(): Similar {
    return Similar(
        imagem = this.imagem,
        titulo = this.titulo,
        dataLancamento = this.dataLancamento
    )
}