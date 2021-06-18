package cl.maleb.testbetterfly.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.maleb.testbetterfly.api.detail.CharacterDetailData
import cl.maleb.testbetterfly.data.repository.CharacterRepository
import cl.maleb.testbetterfly.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repository: CharacterRepository) :
    ViewModel() {

    private val characterDetailMutableLiveData = MutableLiveData<Resource<CharacterDetailData>>()
    val characterDetailLiveData: LiveData<Resource<CharacterDetailData>> =
        characterDetailMutableLiveData

    fun getCharacterDetail(characterIdentifier: String) = viewModelScope.launch {
        repository.getCharacterDetail(characterIdentifier).collect {
            characterDetailMutableLiveData.value = it
        }
    }
}