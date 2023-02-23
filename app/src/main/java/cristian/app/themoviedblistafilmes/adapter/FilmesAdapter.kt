package cristian.app.themoviedblistafilmes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import cristian.app.themoviedblistafilmes.R
import cristian.app.themoviedblistafilmes.helper.Constants
import cristian.app.themoviedblistafilmes.databinding.ItemFilmeBinding
import cristian.app.themoviedblistafilmes.helper.ResultLoading
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI

class FilmesAdapter(
    //private val onClickFilme: (filme: FilmeUI) -> Unit
) : RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder>() {

    private var listaFilmes = mutableListOf<FilmeUI>()
    private lateinit var resultLoading: ResultLoading

    fun recuperandoFilmesPopulares(filmes: List<FilmeUI>){
        this.listaFilmes = filmes.toMutableList()
        notifyDataSetChanged()
    }

    fun setVisibilityProgressBar(resultLoading: ResultLoading) {
        this.resultLoading = resultLoading
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
        return listaFilmes.count()
    }

    inner class FilmesViewHolder(private val binding: ItemFilmeBinding): RecyclerView.ViewHolder(binding.root){

        private var titulo = binding.textTituloPopular
        private val imagem = binding.imageFilmePopular
        private var progressBar = binding.progressItemRV

        fun bind(filme: FilmeUI){
            progressBar.visibility = resultLoading.visibility
            val url = Constants.BASE_IMAGE_URL + "w500" + "${filme.imagem}"

            titulo.text = filme.titulo
            Picasso.get()
                .load(url)
                //.placeholder(R.drawable.imagedefault)
                .into(imagem)

        }

    }
}