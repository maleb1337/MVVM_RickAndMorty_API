package cl.maleb.testbetterfly.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.maleb.testbetterfly.api.list.ResultData
import cl.maleb.testbetterfly.data.repository.CharacterRepository
import cl.maleb.testbetterfly.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val repository: CharacterRepository) :
    ViewModel() {

    private val characterListMutableLiveData = MutableLiveData<Resource<List<ResultData>>>()
    val characterListLiveData: LiveData<Resource<List<ResultData>>> = characterListMutableLiveData

    fun getCharacterList() = viewModelScope.launch {
        repository.getCharacterList().collect {
            characterListMutableLiveData.value = it
        }
    }

}