package cristian.app.themoviedblistafilmes.data.model.similar

import com.google.gson.annotations.SerializedName

data class SimilarResponse(
    @SerializedName("results")
    val listaFilmesSimilares: List<SimilarDTO>
)