package cristian.app.themoviedblistafilmes.presentation.viewmodel

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cristian.app.themoviedblistafilmes.domain.usecase.FilmeUseCase
import cristian.app.themoviedblistafilmes.domain.usecase.IFilmeUseCase
import cristian.app.themoviedblistafilmes.helper.ResultLoading
import cristian.app.themoviedblistafilmes.presentation.model.DetalhesUI
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val iFilmeUseCase: IFilmeUseCase
) : ViewModel() {

    private val progressBar = MutableLiveData<ResultLoading>()
    val progressBarVisibility: MutableLiveData<ResultLoading>
        get() = progressBar

    private val listaFilmeUI = MutableLiveData<List<FilmeUI>>()
    val listaFilmes: MutableLiveData<List<FilmeUI>>
        get() = listaFilmeUI

    private val detalhesUI = MutableLiveData<DetalhesUI>()
    val detalhesUIFilme: MutableLiveData<DetalhesUI>
        get() = detalhesUI

    private val filmesSimilares = MutableLiveData<List<FilmeUI>>()
    val listaFilmesSimilares: MutableLiveData<List<FilmeUI>>
        get() = filmesSimilares

    fun recuperarFilmesPopulares() {
        progressBar.value = ResultLoading(visibility = View.VISIBLE)

        viewModelScope.launch {
            listaFilmeUI.postValue(
                iFilmeUseCase.recuperarFilmesPopulares()
            )
            progressBar.value = ResultLoading(visibility = View.GONE)
        }

    }

    fun recuperarFilmesPesquisa(pesquisaDigitada: String) {
        viewModelScope.launch {
            listaFilmeUI.postValue(
                iFilmeUseCase.recuperarFilmesPesquisa(pesquisaDigitada)
            )
        }
    }

    fun recuperarFilmeDetalhes(movie_id: Int) {
        viewModelScope.launch {
            detalhesUI.postValue(
                iFilmeUseCase.recuperarFilmeDetalhes(movie_id)
            )
        }
    }

    fun recuperandoListaFilmesSimilares(movie_id: Int) {
        viewModelScope.launch {
            filmesSimilares.postValue(
                iFilmeUseCase.recuperandoListaFilmesSimilares(movie_id)
            )
        }
    }

}