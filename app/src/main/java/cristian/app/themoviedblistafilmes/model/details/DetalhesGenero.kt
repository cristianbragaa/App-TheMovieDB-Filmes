package cristian.app.themoviedblistafilmes.model.details

import com.google.gson.annotations.SerializedName

data class DetalhesGenero(
    @SerializedName("id")
    var idGenero: Int,
    @SerializedName("name")
    var nomeGenero: String
)