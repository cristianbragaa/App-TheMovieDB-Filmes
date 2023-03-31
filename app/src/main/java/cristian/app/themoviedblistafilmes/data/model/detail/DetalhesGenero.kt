package cristian.app.themoviedblistafilmes.data.model.detail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetalhesGenero(
    @SerializedName("id")
    var idGenero: Int,
    @SerializedName("name")
    var nomeGenero: String
) : Parcelable