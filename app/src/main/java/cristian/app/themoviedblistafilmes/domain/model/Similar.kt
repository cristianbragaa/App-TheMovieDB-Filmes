package cristian.app.themoviedblistafilmes.domain.model

import com.google.gson.annotations.SerializedName
import cristian.app.themoviedblistafilmes.data.model.similar.SimilarDTO
import cristian.app.themoviedblistafilmes.presentation.model.SimilarUI

data class Similar(
    @SerializedName("poster_path")
    val imagem: String?,
    @SerializedName("original_title")
    val titulo: String,
    @SerializedName("release_date")
    val dataLancamento: String
)

fun Similar.toSimilarUI(): SimilarUI {
    return SimilarUI(
        imagem = this.imagem,
        titulo = this.titulo,
        dataLancamento = this.dataLancamento
    )
}
