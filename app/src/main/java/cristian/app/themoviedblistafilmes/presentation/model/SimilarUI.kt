package cristian.app.themoviedblistafilmes.presentation.model

import com.google.gson.annotations.SerializedName

data class SimilarUI(
    @SerializedName("poster_path")
    val imagem: String,
    @SerializedName("title")
    val titulo: String,
    @SerializedName("release_date")
    val dataLancamento: String
)
