package tickets.solution.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import tickets.loadable.Loadable
import tickets.loadable.Loadable.NotReady
import tickets.loadable.Loadable.Ready

class SolutionResultViewModel(
    resultFlow: Flow<SolutionResult>,
) : ViewModel() {

    private val _state = MutableStateFlow<Loadable<SolutionResultViewState>>(NotReady)

    val state: StateFlow<Loadable<SolutionResultViewState>>
        get() = _state

    init {
        resultFlow
            .onEach { _state.value = Ready(SolutionResultViewState.createFrom(it)) }
            .launchIn(viewModelScope)
    }
}