package cristian.app.themoviedblistafilmes.model.popular

import com.google.gson.annotations.SerializedName

data class FilmesResult(
    @SerializedName("results")
    var listaFilmes: List<Filme>
    )