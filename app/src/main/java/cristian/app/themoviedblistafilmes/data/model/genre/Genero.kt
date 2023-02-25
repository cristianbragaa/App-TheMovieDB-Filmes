package cristian.app.themoviedblistafilmes.data.model.genre

import com.google.gson.annotations.SerializedName

data class Genero(
    @SerializedName("id")
    val idListaGenero: Int,
    @SerializedName("name")
    val nameListaGenero: String
)