package cristian.app.themoviedblistafilmes.model.genres

import com.google.gson.annotations.SerializedName

data class GeneroLista(
    @SerializedName("genres")
    val listaGeneros: List<Genero>
)