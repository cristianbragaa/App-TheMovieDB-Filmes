package cristian.app.themoviedblistafilmes.presentation.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cristian.app.themoviedblistafilmes.domain.usecase.IFilmeUseCase
import cristian.app.themoviedblistafilmes.helper.ResultLoading
import cristian.app.themoviedblistafilmes.presentation.model.DetalhesUI
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI
import cristian.app.themoviedblistafilmes.presentation.model.SimilarUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(
    private val iFilmeUseCase: IFilmeUseCase
) : ViewModel() {

    private val _progressBarVisibility = MutableLiveData<ResultLoading>()
    val progressBarVisibility: LiveData<ResultLoading>
        get() = _progressBarVisibility

    private val _listaFilmeUI = MutableLiveData<List<FilmeUI>>()
    val listaFilmes: LiveData<List<FilmeUI>>
        get() = _listaFilmeUI

    private val _detalhesUI = MutableLiveData<DetalhesUI>()
    val detalhesUI: LiveData<DetalhesUI>
        get() = _detalhesUI

    private val _similaresUI = MutableLiveData<List<SimilarUI>>()
    val similaresUI: LiveData<List<SimilarUI>>
        get() = _similaresUI

    fun recuperarFilmesPopulares() {
        viewModelScope.launch {
            _progressBarVisibility.value = ResultLoading(visibility = View.VISIBLE)
            _listaFilmeUI.postValue(iFilmeUseCase.recuperarFilmesPopulares())
            _progressBarVisibility.value = ResultLoading(visibility = View.GONE)
        }
    }

    fun recuperarFilmesPesquisa(pesquisaDigitada: String) = viewModelScope.launch {
        _listaFilmeUI.postValue(
            iFilmeUseCase.recuperarFilmesPesquisa(pesquisaDigitada)
        )
    }

    fun recuperarFilmeDetalhes(movieId: Int) = viewModelScope.launch {
        _progressBarVisibility.value = ResultLoading(visibility = View.VISIBLE)
        _detalhesUI.postValue(
            iFilmeUseCase.recuperarFilmeDetalhes(movieId)
        )
        _progressBarVisibility.value = ResultLoading(visibility = View.GONE)

    }

    fun recuperandoListaFilmesSimilares(movieId: Int) = viewModelScope.launch {
        _progressBarVisibility.value = ResultLoading(visibility = View.VISIBLE)
        _similaresUI.postValue(iFilmeUseCase.recuperandoListaFilmesSimilares(movieId))
        _progressBarVisibility.value = ResultLoading(visibility = View.GONE)
    }

}