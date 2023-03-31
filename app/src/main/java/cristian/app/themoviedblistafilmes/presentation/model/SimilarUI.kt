package cristian.app.themoviedblistafilmes.presentation.model

import com.google.gson.annotations.SerializedName

data class SimilarUI(
    @SerializedName("poster_path")
    var imagem: String?,
    @SerializedName("original_title")
    var titulo: String,
    @SerializedName("release_date")
    var dataLancamento: String
)
