package cristian.app.themoviedblistafilmes.data.model.genre

import com.google.gson.annotations.SerializedName

data class Genero(
    @SerializedName("id")
    val idListaGeneros: Int,
    @SerializedName("name")
    val nameListaGeneros: String
)