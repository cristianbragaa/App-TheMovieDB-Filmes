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
    private val onClickFilme: (FilmeUI) -> Unit
) : RecyclerView.Adapter<FilmesAdapter.FilmesViewHolder>() {

    private var listaFilmes = emptyList<FilmeUI>()
    private lateinit var resultLoading: ResultLoading
    fun recuperandoFilmes(filmes: List<FilmeUI>) {
        this.listaFilmes = filmes
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
        val filmeUI = listaFilmes[position]
        holder.bind(filmeUI, onClickFilme)
    }

    override fun getItemCount() = listaFilmes.count()

    inner class FilmesViewHolder(private val binding: ItemFilmeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val titulo = binding.textTituloPopular
        private val imagem = binding.imageFilmePopular
        private val progressBar = binding.progressItemRV

        fun bind(filmeUI: FilmeUI, onClickFilme: (filme: FilmeUI) -> Unit) {
            progressBar.visibility = resultLoading.visibility

            titulo.text = filmeUI.titulo
            Picasso.get()
                .load(Constants.BASE_IMAGE_URL + "w500" + filmeUI.imagem)
                .into(imagem)

            itemView.setOnClickListener {
                onClickFilme(filmeUI)
            }
        }
    }
}