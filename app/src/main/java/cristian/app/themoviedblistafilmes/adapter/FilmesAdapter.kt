package cristian.app.themoviedblistafilmes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import cristian.app.themoviedblistafilmes.helper.Constants
import cristian.app.themoviedblistafilmes.databinding.ItemFilmeBinding
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI

class FilmesAdapter(
    //private val onClickFilme: (filme: FilmeUI) -> Unit
) : RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder>() {

    private var listaFilmes = mutableListOf<FilmeUI>()

    fun recuperandoFilmesPopulares(filmes: List<FilmeUI>){
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
        holder.bind(listaFilmes[position])
    }

    override fun getItemCount(): Int {
        return listaFilmes.size
    }

    inner class FilmesViewHolder(private val binding: ItemFilmeBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(filme: FilmeUI){
            binding.textTituloPopular.text = filme.titulo
            Picasso.get()
                .load(Constants.BASE_IMAGE_URL + "w500" + filme.imagem)
                .into(binding.imageFilmePopular)

            /*itemView.setOnClickListener {
                onClickFilme(filme)
            }*/
        }
    }
}