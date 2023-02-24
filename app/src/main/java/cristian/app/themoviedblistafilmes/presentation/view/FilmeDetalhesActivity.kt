package cristian.app.themoviedblistafilmes.presentation.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.squareup.picasso.Picasso
import cristian.app.themoviedblistafilmes.adapter.FilmesSimilaresAdapter
import cristian.app.themoviedblistafilmes.databinding.ActivityFilmeDetalhesBinding
import cristian.app.themoviedblistafilmes.helper.Constants
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI
import cristian.app.themoviedblistafilmes.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class FilmeDetalhesActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFilmeDetalhesBinding.inflate(layoutInflater) }
    private lateinit var adapterSimilar: FilmesSimilaresAdapter
    private var id: Int? = null

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        setContentView(binding.root)
        supportActionBar?.title = "Detalhes Filme"

        inicializarBundle()
        //inicializarAdapter()
        inicializarObservables()
    }

    private fun inicializarBundle() {
        val bundle = intent.extras
        id = bundle?.getInt("id")
    }

    /*private fun inicializarAdapter() {
        adapterSimilar = FilmesSimilaresAdapter()

        with(binding) {
            rvSimilares.adapter = adapterSimilar
            rvSimilares.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            rvSimilares.addItemDecoration(
                DividerItemDecoration(applicationContext, RecyclerView.VERTICAL)
            )
        }
    }*/

    private fun inicializarObservables() {
        mainViewModel.detalhesUIFilme.observe(this) { detalhesUI ->
            with(binding){
                textTitulo.text = detalhesUI.titulo
                textQtdVotos.text = "Vote count: ${detalhesUI.qtdVotos}"
                textPopularidade.text = "Popularity: ${detalhesUI.popularidade}"
                textResumo.text = "Resume: ${detalhesUI.resumo}"

                val formatoEntrada = SimpleDateFormat("yyyy-MM-dd")
                val formatoSaida = SimpleDateFormat("dd/MM/yyyy")

                val dataEntrada = formatoEntrada.parse(detalhesUI.dataLancamento.toString())
                val dataSaida = formatoSaida.format(dataEntrada)
                textData.text = "Release Date: $dataSaida"

                Picasso.get()
                    .load(Constants.BASE_IMAGE_URL + "w500" + detalhesUI.imagem)
                    .into(imageDetalhes)
            }
        }
        mainViewModel.recuperarFilmeDetalhes(id!!)
    }

    private fun installSplash() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            return@setKeepOnScreenCondition false
        }
    }

/*

private suspend fun recuperandoDetalhesFilme() {
    var response: Response<Detalhes>? = null
    try {
        response = retrofit.getDetailsMovies(id!!)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    if (response != null) {
        if (response.isSuccessful) {
            detalhes = response.body()

            val formatoEntrada = SimpleDateFormat("yyyy-MM-dd")
            val formatoSaida = SimpleDateFormat("dd/MM/yyyy")

            val dataLancamento = detalhes?.dataLancamento
            val dataEntrada = formatoEntrada.parse(dataLancamento)
            val resultado = formatoSaida.format(dataEntrada)

            withContext(Dispatchers.Main) {
                if (detalhes != null) {
                    Picasso.get()
                        .load(RetrofitInstance.BASE_IMAGE_URL + "w500" + detalhes?.imagem)
                        .into(binding.imageDetalhes)

                    binding.textTitulo.text = detalhes?.titulo
                    binding.textData.text = "Release Date: $resultado"
                    binding.textResumo.text = "Resume: ${detalhes?.resumo}"
                    binding.textPopularidade.text = "Popularity: ${detalhes?.popularidade}"
                    binding.textQtdVotos.text = "Vote count: ${detalhes?.qtdVotos}"
                }
            }
        }
    }
}

private suspend fun recuperandoListaGeneros() {
    var response: Response<GeneroLista>? = null
    try {
        response = retrofit.getListGenres()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    if (response != null) {
        if (response.isSuccessful) {
            val listaGeneros = response.body()

            val listGeneros = listaGeneros?.listaGeneros
            listGeneros?.forEach { genero ->
                generosId?.forEach { idGeneros ->
                    if (idGeneros == genero.idListaGeneros) {

                        withContext(Dispatchers.Main) {
                            binding.textGenero.text = "Genre: ${genero.nameListaGeneros}"
                            Log.i("info_generos", "Generos: ${genero.nameListaGeneros} ")
                        }
                    }
                }
            }
        }
    }
}

private suspend fun recuperandoListaFilmesSimilares() {
    var response: Response<FilmesResult>? = null
    try {
        response = retrofit.getSimilarMovies(id!!)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    if (response != null) {
        if (response.isSuccessful) {
            val similares = response.body()
            val filmesSimilares = similares?.listaFilmes

            withContext(Dispatchers.Main) {
                if (filmesSimilares != null) {
                    adapterSimilar.recuperandoFilmesSimilares(filmesSimilares)
                }
            }
        }
    }
}

 */


}
