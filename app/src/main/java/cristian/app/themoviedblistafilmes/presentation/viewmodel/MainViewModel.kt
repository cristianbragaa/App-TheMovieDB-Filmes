package cristian.app.themoviedblistafilmes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import cristian.app.themoviedblistafilmes.domain.usecase.IFilmeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainViewModel(
    private val iFilmeUseCase: IFilmeUseCase
) : ViewModel() {

    

}