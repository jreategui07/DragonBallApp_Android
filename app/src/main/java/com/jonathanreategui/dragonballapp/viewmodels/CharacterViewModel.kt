package com.jonathanreategui.dragonballapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.jonathanreategui.dragonballapp.data.remote.ApiInterface
import com.jonathanreategui.dragonballapp.data.remote.RetrofitInstance
import com.jonathanreategui.dragonballapp.data.models.CharactersResponse
import com.jonathanreategui.dragonballapp.data.models.DragonBallCharacter

class CharacterViewModel : ViewModel() {

    var api: ApiInterface = RetrofitInstance.retrofitService

    private val _characters = MutableLiveData<CharactersResponse>()
    val characters: LiveData<CharactersResponse> get() = _characters

    private val _selectedCharacter = MutableLiveData<DragonBallCharacter?>()
    val selectedCharacter: LiveData<DragonBallCharacter?> get() = _selectedCharacter

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun fetchCharacters() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.retrofitService.getCharacters()
                _characters.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
                _error.postValue("Failed fetching characters${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun selectCharacter(dbCharacter: DragonBallCharacter) {
        _selectedCharacter.postValue(dbCharacter)
    }
}
