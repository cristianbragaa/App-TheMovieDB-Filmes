package cristian.app.themoviedblistafilmes.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cristian.app.themoviedblistafilmes.adapter.FilmesSimilaresAdapter
import cristian.app.themoviedblistafilmes.databinding.ActivityFilmeDetalhesBinding
import cristian.app.themoviedblistafilmes.data.model.detail.Detalhes
import cristian.app.themoviedblistafilmes.data.model.popular.FilmeResultado
import cristian.app.themoviedblistafilmes.data.service.RetrofitInstance


class FilmeDetalhesActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFilmeDetalhesBinding.inflate(layoutInflater) }
    private val retrofit by lazy { RetrofitInstance.filmesAPI }
    private lateinit var adapterSimilar: FilmesSimilaresAdapter

    private var filme: FilmeResultado? = null
    private var id: Int? = null
    private var detalhes: Detalhes? = null
    private var generosId: List<Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition(SplashScreen.KeepOnScreenCondition {
            false
        })
        setContentView(binding.root)
        supportActionBar?.title = "Detalhes Filme"

        val bundle = intent.extras
        if (bundle != null) {
            filme = bundle.getParcelable("filme")
            id = filme?.id
            generosId = filme?.generos
        }

        configuraAdapter()
    }

    private fun configuraAdapter() {
        adapterSimilar = FilmesSimilaresAdapter()
        binding.rvSimilares.adapter = adapterSimilar
        binding.rvSimilares.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvSimilares.addItemDecoration(
            DividerItemDecoration(
                this,
                RecyclerView.VERTICAL
            )
        )
    }

    /*private fun recuperandoDados() {
        CoroutineScope(Dispatchers.IO).launch {
            recuperandoDetalhesFilme()
            recuperandoListaGeneros()
            recuperandoListaFilmesSimilares()
        }
    }

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

    override fun onStart() {
        super.onStart()
        recuperandoDados()
    }*/
}