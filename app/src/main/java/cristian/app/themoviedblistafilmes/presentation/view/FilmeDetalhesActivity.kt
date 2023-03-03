package cristian.app.themoviedblistafilmes.presentation.view

import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import cristian.app.themoviedblistafilmes.adapter.FilmesSimilaresAdapter
import cristian.app.themoviedblistafilmes.databinding.ActivityFilmeDetalhesBinding
import cristian.app.themoviedblistafilmes.helper.Constants
import cristian.app.themoviedblistafilmes.presentation.model.DetalhesUI
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
        inicializarAdapter()
        inicializarObservables()
    }

    private fun inicializarBundle() {
        val bundle = intent.extras
        id = bundle?.getInt("id")

    }

    private fun inicializarAdapter() {
        adapterSimilar = FilmesSimilaresAdapter()

        with(binding) {
            rvSimilares.adapter = adapterSimilar
            rvSimilares.layoutManager = LinearLayoutManager(
                applicationContext, RecyclerView.VERTICAL, false
            )
            rvSimilares.addItemDecoration(
                DividerItemDecoration(applicationContext, RecyclerView.VERTICAL)
            )
        }
    }

    private fun inicializarObservables() {
        mainViewModel.detalhesUI.observe(this) { detalhesUI ->
            configuraTelaDetalhes(detalhesUI)
        }
        mainViewModel.recuperarFilmeDetalhes(id!!)

        mainViewModel.progressBarVisibility.observe(this) { resultLoading ->
            binding.progressDetalhes.visibility = resultLoading.visibility
            adapterSimilar.setVisibilityProgressBar(resultLoading)
        }

        mainViewModel.similaresUI.observe(this) { listaSimilarUI ->
            adapterSimilar.recuperandoFilmesSimilares(listaSimilarUI)
        }
        mainViewModel.recuperandoListaFilmesSimilares(id!!)
    }

    private fun configuraTelaDetalhes(detalhesUI: DetalhesUI) {
        with(binding) {
            textTitulo.text = detalhesUI.titulo
            textQtdVotos.text = "Vote count: ${detalhesUI.qtdVotos}"
            textPopularidade.text = "Popularity: ${detalhesUI.popularidade}"
            textResumo.text = "Resume: ${detalhesUI.resumo}"

            val formatoEntrada = SimpleDateFormat("yyyy-MM-dd")
            val formatoSaida = SimpleDateFormat("dd/MM/yyyy")

            val dataEntrada = formatoEntrada.parse(detalhesUI.dataLancamento.toString())
            val dataSaida = formatoSaida.format(dataEntrada)
            textData.text = "Release Date: $dataSaida"

            val genero = detalhesUI.generos.map {
                it.nomeGenero
            }

            if (genero.isNotEmpty()) {
                textGenero.text = "Genre: ${genero[0].removeSurrounding("[", "]")}"
            } else {
                textGenero.text = "- -"
            }


            Picasso.get()
                .load(Constants.BASE_IMAGE_URL + "w780" + detalhesUI.imagem)
                .into(imageDetalhes)
        }
    }

    private fun installSplash() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            return@setKeepOnScreenCondition false
        }
    }

}
