package com.blueberryprojects.kotlinflowslivedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val _liveData: MutableLiveData<Int> = MutableLiveData(0)
    val liveData: LiveData<Int> = _liveData

    private val _stateFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    val stateFlow: StateFlow<Int> = _stateFlow.asStateFlow()

    private val _sharedFlow: MutableSharedFlow<String> = MutableSharedFlow()
    val sharedFlow: SharedFlow<String> = _sharedFlow.asSharedFlow()

    fun startLiveData() {
        _liveData.value = _liveData.value?.plus(1)
    }

    fun startStateFlow() {
        _stateFlow.value += 1
    }

    fun startSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("I'm a SharedFlow!")
        }
    }

    fun startFlow() = flow {
        repeat(10) {
            val value = it + 1
            delay(1000L)
            emit(value)
        }
    }
}