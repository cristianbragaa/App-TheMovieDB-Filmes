package cristian.app.themoviedblistafilmes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import cristian.app.themoviedblistafilmes.R
import cristian.app.themoviedblistafilmes.helper.Constants
import cristian.app.themoviedblistafilmes.databinding.ItemSimilarBinding
import cristian.app.themoviedblistafilmes.helper.ResultLoading
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI
import cristian.app.themoviedblistafilmes.presentation.model.SimilarUI
import java.lang.Exception
import java.text.SimpleDateFormat

class FilmesSimilaresAdapter :
    RecyclerView.Adapter<FilmesSimilaresAdapter.FilmesSimilaresViewHolder>() {

    private var listaFilmesSimilares = emptyList<SimilarUI>()
    private lateinit var resultLoading: ResultLoading

    fun recuperandoFilmesSimilares(filmesSimilares: List<SimilarUI>) {
        this.listaFilmesSimilares = filmesSimilares
        notifyDataSetChanged()
    }

    fun setVisibilityProgressBar(resultLoading: ResultLoading) {
        this.resultLoading = resultLoading
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesSimilaresViewHolder {
        val binding = ItemSimilarBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FilmesSimilaresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmesSimilaresViewHolder, position: Int) {
        val similarUI = listaFilmesSimilares[position]
        holder.bind(similarUI)
    }

    override fun getItemCount() = listaFilmesSimilares.count()

    inner class FilmesSimilaresViewHolder(private val binding: ItemSimilarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val progressBar = binding.progressItemSimilar
        private val textDataSimilar = binding.textDataSimilar
        private val textTituloSimilar = binding.textTituloSimilar
        private val imagem = binding.imageCapa

        fun bind(similarUI: SimilarUI) {
            progressBar.visibility = resultLoading.visibility

            val formatoEntrada = SimpleDateFormat("yyyy-MM-dd")
            val formatoSaida = SimpleDateFormat("dd/MM/yyyy")

            val dataLancamento = similarUI.dataLancamento
            if (dataLancamento.isNotEmpty()) {
                val dataEntrada = formatoEntrada.parse(dataLancamento)
                val dataSaida = formatoSaida.format(dataEntrada)
                textDataSimilar.text = "Release date: $dataSaida"
            } else {
                textDataSimilar.text = "Release date: - -"
            }

            textTituloSimilar.text = similarUI.titulo
            Picasso.get()
                .load(Constants.BASE_IMAGE_URL + "w500" + similarUI.imagem)
                .error(R.drawable.imagedefault)
                .into(imagem)
        }
    }
}