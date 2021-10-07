package tickets.solution.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tickets.solution.result.SolutionResultViewState.NotReady

class SolutionResultViewModel(
    private val resultFlow: Flow<SolutionResult>,
) : ViewModel() {

    private val _state = MutableStateFlow<SolutionResultViewState>(value = NotReady)

    val state: StateFlow<SolutionResultViewState>
        get() = _state

    init {
        viewModelScope.launch {
            resultFlow.collect { _state.value = SolutionResultViewState.createFrom(it) }
        }
    }
}