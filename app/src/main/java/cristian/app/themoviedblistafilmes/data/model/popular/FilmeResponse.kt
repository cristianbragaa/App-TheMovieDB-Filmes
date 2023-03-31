package cristian.app.themoviedblistafilmes.data.model.popular

import com.google.gson.annotations.SerializedName

data class FilmeResponse(
    @SerializedName("results")
    var listaFilmeDTO: List<FilmeDTO>,
    @SerializedName("total_results")
    var resultadoTotal: Int
)