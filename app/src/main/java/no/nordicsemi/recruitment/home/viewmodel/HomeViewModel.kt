package no.nordicsemi.recruitment.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import no.nordicsemi.recruitment.home.repository.StorageRepository
import no.nordicsemi.recruitment.home.view.FieldError
import no.nordicsemi.recruitment.home.view.HomeViewState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: StorageRepository
) : ViewModel() {

    private val _timer = MutableStateFlow(0)
    val timer = _timer.asStateFlow()

    private val _tasks = MutableStateFlow(HomeViewState(tasks = repository.tasks))
    val tasks = _tasks.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            //Coroutine is attached to viewModelScope so it will be canceled after view model is destroyed.
            while (true) {
                delay(1000)
                _timer.value = _timer.value + 1
            }
        }
    }

    fun onTextChanged(value: String) {
        val oldState = _tasks.value
        _tasks.value = oldState.copy(displayedText = value, error = null)
    }

    fun addNewTask() {
        val currentState = _tasks.value

        val newState = if (currentState.displayedText.isEmpty()) {
            currentState.copy(error = FieldError.EMPTY)
        } else if (currentState.tasks.contains(currentState.displayedText)) {
            currentState.copy(error = FieldError.ALREADY_EXIST)
        } else {
            val newTasks = currentState.tasks + currentState.displayedText
            repository.tasks = _tasks.value.tasks
            currentState.copy(tasks = newTasks)
        }

        _tasks.value = newState
    }
}
