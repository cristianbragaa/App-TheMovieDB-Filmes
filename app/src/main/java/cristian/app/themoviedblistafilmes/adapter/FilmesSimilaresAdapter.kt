package cristian.app.themoviedblistafilmes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import cristian.app.themoviedblistafilmes.databinding.ItemSimilarBinding
import cristian.app.themoviedblistafilmes.data.model.popular.FilmeDTO
import cristian.app.themoviedblistafilmes.data.service.OkhttpClientInterceptor
import java.text.SimpleDateFormat

class FilmesSimilaresAdapter : RecyclerView.Adapter<FilmesSimilaresAdapter.FilmesSimilaresViewHolder>() {

    private var listaFilmesSimilares = mutableListOf<FilmeDTO>()

    fun recuperandoFilmesSimilares(filmesSimilares: List<FilmeDTO>){
        this.listaFilmesSimilares = filmesSimilares.toMutableList()
        notifyDataSetChanged()
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
        holder.bind(listaFilmesSimilares[position])
    }

    override fun getItemCount(): Int {
        return listaFilmesSimilares.size
    }

    inner class FilmesSimilaresViewHolder(private val binding: ItemSimilarBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(filme: FilmeDTO){
            val formatoEntrada = SimpleDateFormat("yyyy-MM-dd")
            val formatoSaida = SimpleDateFormat("dd/MM/yyyy")

            val dataLancamento = filme.dataLancamento

            if (dataLancamento != null && dataLancamento != "") {
                val dataEntrada = formatoEntrada.parse(dataLancamento)
                val dataSaida = formatoSaida.format(dataEntrada)
                binding.textDataSimilar.text = "Release date: $dataSaida"
            } else {
                binding.textDataSimilar.text = "Release date: - -"
            }

            binding.textTituloSimilar.text = filme.titulo
            Picasso.get()
                .load(OkhttpClientInterceptor.BASE_IMAGE_URL + "w500" + filme.imagem)
                .into(binding.imageCapa)
        }
    }
}