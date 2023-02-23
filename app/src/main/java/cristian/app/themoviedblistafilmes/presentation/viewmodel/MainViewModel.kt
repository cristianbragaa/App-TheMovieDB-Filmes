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
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val iFilmeUseCase: IFilmeUseCase
) : ViewModel() {

    private val liveDataProgress = MutableLiveData<ResultLoading>()
    val progressBarVisibility: MutableLiveData<ResultLoading>
        get() = liveDataProgress


    private val livedata = MutableLiveData<List<FilmeUI>>()
    val listaFilmesPopulares: MutableLiveData<List<FilmeUI>>
        get() = livedata

    private val livedataCampoPesquisa = MutableLiveData<List<FilmeUI>>()
    val listaFilmesPesquisa: MutableLiveData<List<FilmeUI>>
        get() = livedataCampoPesquisa

    fun recuperarFilmesPopulares() {
        liveDataProgress.value = ResultLoading(visibility = View.VISIBLE)
        viewModelScope.launch {
            livedata.postValue(
                iFilmeUseCase.recuperarFilmesPopulares()
            )
        }
        liveDataProgress.value = ResultLoading(visibility = View.GONE)
    }

    fun recuperarFilmesPesquisa(pesquisaDigitada: String) {
        viewModelScope.launch {
            livedataCampoPesquisa.postValue(
                iFilmeUseCase.recuperarFilmesPesquisa(pesquisaDigitada)
            )
        }
    }

}