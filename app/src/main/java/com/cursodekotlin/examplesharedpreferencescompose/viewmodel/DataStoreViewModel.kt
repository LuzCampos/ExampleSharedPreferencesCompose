package com.cursodekotlin.examplesharedpreferencescompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursodekotlin.examplesharedpreferencescompose.repository.DataStorePreferenceRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DataStoreViewModel( private val dataStorePreferenceRepository: DataStorePreferenceRepository) : ViewModel() {
    private val _userName = MutableLiveData("")
    val userName: LiveData<String> = _userName

    init {
        viewModelScope.launch {
            dataStorePreferenceRepository.getUserName.collect {
                _userName.value = it
            }
        }
    }

    suspend fun saveUserName(userName: String) {
        dataStorePreferenceRepository.setUserName(userName)
    }
}