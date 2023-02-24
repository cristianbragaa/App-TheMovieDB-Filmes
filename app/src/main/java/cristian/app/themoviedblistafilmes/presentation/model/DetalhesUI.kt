package cristian.app.themoviedblistafilmes.presentation.model

import android.os.Bundle
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import cristian.app.themoviedblistafilmes.data.model.detail.DetalhesGenero
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class DetalhesUI(
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
) : Parcelable

