package cristian.app.themoviedblistafilmes.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cristian.app.themoviedblistafilmes.adapter.FilmesAdapter
import cristian.app.themoviedblistafilmes.databinding.ActivityMainBinding
import cristian.app.themoviedblistafilmes.model.popular.Filme
import cristian.app.themoviedblistafilmes.model.popular.FilmesResult
import cristian.app.themoviedblistafilmes.retrofit.RetrofitInstance
import kotlinx.coroutines.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val retrofit by lazy { RetrofitInstance.filmesAPI }

    private lateinit var adapterFilmes: FilmesAdapter
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition(SplashScreen.KeepOnScreenCondition {
            false
        })
        setContentView(binding.root)
        supportActionBar?.hide()

        configuraAdapter()
        configuraAlternarButtonPesquisa()
    }

    private fun configuraAlternarButtonPesquisa() {
        binding.btnPesquisa.setOnClickListener {
            if (binding.textoPesquisa.text.toString() == "") {
                CoroutineScope(Dispatchers.IO).launch {
                    recuperaFilmesPopulares()
                }
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    recuperaCampoPesquisa()
                }
            }
        }
    }

    private fun configuraAdapter() {
        with(binding) {
            adapterFilmes = FilmesAdapter { filme ->
                val intent = Intent(applicationContext, FilmeDetalhesActivity::class.java)
                intent.putExtra("filme", filme)
                startActivity(intent)
            }
            rvFilmes.adapter = adapterFilmes
            rvFilmes.layoutManager = GridLayoutManager(
                applicationContext, 3, RecyclerView.VERTICAL, false
            )
        }
    }

    private fun recuperandoDadosFilmes() {
        job = CoroutineScope(Dispatchers.IO).launch {
            recuperaFilmesPopulares()
        }
    }

    private suspend fun recuperaFilmesPopulares() {
        var response: Response<FilmesResult>? = null
        try {
            response = retrofit.getPopularMovies()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (response != null) {
            if (response.isSuccessful) {

                val listaFilmes = response.body()?.listaFilmes
                if (listaFilmes != null) {
                    withContext(Dispatchers.Main) {
                        adapterFilmes.recuperandoFilmesPopulares(listaFilmes)

                    }
                }
            }
        }
    }

    private suspend fun recuperaCampoPesquisa() {
        val textoCampoPesquisa = binding.textoPesquisa.text.toString()

        var response: Response<FilmesResult>? = null
        try {
            response = retrofit.getSearchMovie(textoCampoPesquisa)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (response != null) {
            if (response.isSuccessful) {
                val listaFilmes = response.body()?.listaFilmes

                val listaFilmesRecuperados = mutableListOf<Filme>()

                listaFilmes?.forEach { filme ->
                    if (filme.imagem != null) {
                        listaFilmesRecuperados.add(filme)
                    }

                    withContext(Dispatchers.Main) {
                        adapterFilmes.recuperandoFilmesPopulares(listaFilmesRecuperados)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        recuperandoDadosFilmes()
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
    }
}
