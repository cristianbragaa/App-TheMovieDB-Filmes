package cristian.app.themoviedblistafilmes.presentation.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilmeUI(
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
) : Parcelable
