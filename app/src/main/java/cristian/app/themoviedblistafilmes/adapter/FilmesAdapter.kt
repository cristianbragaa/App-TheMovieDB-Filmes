package cristian.app.themoviedblistafilmes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import cristian.app.themoviedblistafilmes.constants.Constants
import cristian.app.themoviedblistafilmes.databinding.ItemFilmeBinding
import cristian.app.themoviedblistafilmes.data.model.popular.FilmeDTO
import cristian.app.themoviedblistafilmes.data.service.OkhttpClientInterceptor

class FilmesAdapter(
    private val onClickFilme: (filme: FilmeDTO) -> Unit
) : RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder>() {

    private var listaFilmes = mutableListOf<FilmeDTO>()

    fun recuperandoFilmesPopulares(filmes: List<FilmeDTO>){
        this.listaFilmes = filmes.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {
        val binding = ItemFilmeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FilmesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        holder.bind(listaFilmes[position], onClickFilme)
    }

    override fun getItemCount(): Int {
        return listaFilmes.size
    }

    inner class FilmesViewHolder(private val binding: ItemFilmeBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(filme: FilmeDTO, onClickFilme: (filme: FilmeDTO) -> Unit){
            binding.textTituloPopular.text = filme.titulo
            Picasso.get()
                .load(Constants.BASE_IMAGE_URL + "w500" + filme.imagem)
                .into(binding.imageFilmePopular)

            itemView.setOnClickListener {
                onClickFilme(filme)
            }
        }
    }
}