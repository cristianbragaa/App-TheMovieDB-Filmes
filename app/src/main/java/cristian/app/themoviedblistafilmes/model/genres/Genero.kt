package cristian.app.themoviedblistafilmes.model.genres

import com.google.gson.annotations.SerializedName

data class Genero(
    @SerializedName("id")
    val idListaGeneros: Int,
    @SerializedName("name")
    val nameListaGeneros: String
)