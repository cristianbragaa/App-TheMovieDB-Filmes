package cristian.app.themoviedblistafilmes.data.model.genre

import com.google.gson.annotations.SerializedName
import cristian.app.themoviedblistafilmes.data.model.genre.Genero

data class GeneroLista(
    @SerializedName("genres")
    val listaGeneros: List<Genero>
)