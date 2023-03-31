package cristian.app.themoviedblistafilmes.presentation.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import cristian.app.themoviedblistafilmes.data.model.detail.DetalhesGenero
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetalhesUI(
    @SerializedName("poster_path")
    val imagem: String,
    @SerializedName("genres")
    val generos: List<DetalhesGenero>,
    @SerializedName("overview")
    val resumo: String,
    @SerializedName("popularity")
    val popularidade: Double,
    @SerializedName("release_date")
    val dataLancamento: String,
    @SerializedName("title")
    val titulo: String,
    @SerializedName("vote_count")
    val qtdVotos: Int
) : Parcelable

