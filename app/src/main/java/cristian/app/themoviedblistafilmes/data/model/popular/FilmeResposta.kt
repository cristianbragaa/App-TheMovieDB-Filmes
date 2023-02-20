package cristian.app.themoviedblistafilmes.data.model.popular

import com.google.gson.annotations.SerializedName

data class FilmeResposta(
    @SerializedName("results")
    var listaFilmes: List<FilmeResultado>
)