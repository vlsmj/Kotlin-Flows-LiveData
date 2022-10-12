package com.blueberryprojects.kotlinflowslivedata

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

class MyViewModel : ViewModel() {

    private val _stateFlow: MutableStateFlow<Int> = MutableStateFlow(0)
    val stateFlow: StateFlow<Int> = _stateFlow.asStateFlow()

    private val _sharedFlow: MutableSharedFlow<Int> = MutableSharedFlow()
    val sharedFlow: SharedFlow<Int> = _sharedFlow

    private var job: Job? = null

    fun cancelJob() {
        job?.let {
            if (it.isActive) {
                it.cancel()
            }
        }
    }

    fun startStateFlow() {
        _stateFlow.value += 1
    }
}