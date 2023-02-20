package cristian.app.themoviedblistafilmes.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cristian.app.themoviedblistafilmes.domain.usecase.FilmeUseCase
import cristian.app.themoviedblistafilmes.domain.usecase.IFilmeUseCase
import cristian.app.themoviedblistafilmes.presentation.model.FilmeUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val iFilmeUseCase: IFilmeUseCase
) : ViewModel() {

    private val livedata = MutableLiveData<List<FilmeUI>>()

    val listaFilmesUI: MutableLiveData<List<FilmeUI>>
        get() = livedata

    init {
        recuperarFilmesPopulares()
    }

    fun recuperarFilmesPopulares() = viewModelScope.launch {
        livedata.postValue(iFilmeUseCase.recuperarFilmesPopulares())
    }

}