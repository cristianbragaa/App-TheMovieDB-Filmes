package cristian.app.themoviedblistafilmes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import cristian.app.themoviedblistafilmes.databinding.ItemFilmeBinding
import cristian.app.themoviedblistafilmes.model.genres.Genero
import cristian.app.themoviedblistafilmes.model.popular.Filme
import cristian.app.themoviedblistafilmes.retrofit.RetrofitInstance

class FilmesAdapter(
    private val onClickFilme: (filme: Filme) -> Unit
) : RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder>() {

    private var listaFilmes = mutableListOf<Filme>()

    fun recuperandoFilmesPopulares(filmes: List<Filme>){
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

        fun bind(filme: Filme, onClickFilme: (filme: Filme) -> Unit){
            binding.textTituloPopular.text = filme.titulo
            Picasso.get()
                .load(RetrofitInstance.BASE_IMAGE_URL + "w500" + filme.imagem)
                .into(binding.imageFilmePopular)

            itemView.setOnClickListener {
                onClickFilme(filme)
            }
        }
    }
}