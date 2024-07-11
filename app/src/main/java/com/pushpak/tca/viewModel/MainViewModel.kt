package com.pushpak.tca.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pushpak.tca.action.CounterAction
import com.pushpak.tca.model.CounterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel:ViewModel()   {
    fun counterReducer(state: CounterState, action: CounterAction): CounterState {
        return when (action) {
            is CounterAction.Increment -> state.copy(count = state.count + 1)
            is CounterAction.Decrement -> state.copy(count = state.count - 1)
        }
    }
    private val _state = MutableStateFlow(CounterState())
    val state:StateFlow<CounterState> = _state.asStateFlow()

    fun dispatch(action: CounterAction){
        viewModelScope.launch {
            val newState = counterReducer(_state.value, action)
            _state.value = newState
        }
    }
}