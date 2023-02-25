package cristian.app.themoviedblistafilmes.presentation.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cristian.app.themoviedblistafilmes.adapter.FilmesAdapter
import cristian.app.themoviedblistafilmes.databinding.ActivityMainBinding
import cristian.app.themoviedblistafilmes.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var adapterFilmes: FilmesAdapter

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        setContentView(binding.root)
        supportActionBar?.hide()

        inicializarAdapter()
        inicializarObservables()
    }

    private fun inicializarAdapter() {
        with(binding) {
            adapterFilmes = FilmesAdapter { filmeUI ->
                val intent = Intent(applicationContext, FilmeDetalhesActivity::class.java)
                intent.putExtra("id", filmeUI.id)
                startActivity(intent)
            }
            rvFilmes.adapter = adapterFilmes
            rvFilmes.layoutManager = GridLayoutManager(
                applicationContext, 3, RecyclerView.VERTICAL, false
            )
        }
    }

    private fun inicializarObservables() {
        // Filmes Populares
        mainViewModel.listaFilmes.observe(this) { listaFilmesPopulares ->
            adapterFilmes.recuperandoFilmes(listaFilmesPopulares)
        }
        mainViewModel.recuperarFilmesPopulares()

        // Configura ProgressBar
        mainViewModel.progressBarVisibility.observe(this) { resultLoading ->
            adapterFilmes.setVisibilityProgressBar(resultLoading)
        }

        // Filmes Campo Pesquisar
        mainViewModel.listaFilmes.observe(this) { listaFilmesPesquisa ->
            adapterFilmes.recuperandoFilmes(listaFilmesPesquisa)
        }
        inicializarListener()
    }

    private fun inicializarListener() {
        binding.btnPesquisa.setOnClickListener {
            val pesquisaDigitada = binding.textoPesquisa.text.toString()

            if (pesquisaDigitada.isNotEmpty()) {
                mainViewModel.recuperarFilmesPesquisa(pesquisaDigitada)
                //Esconder teclado após pesquisa
                val inputMethodMenager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodMenager.hideSoftInputFromWindow(it.windowToken, 0)
            } else {
                Toast.makeText(
                    this,
                    "É necessário preencher um valor para o campo pesquisa",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun installSplash() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            Thread.sleep(2000L)
            return@setKeepOnScreenCondition false
        }
    }

}