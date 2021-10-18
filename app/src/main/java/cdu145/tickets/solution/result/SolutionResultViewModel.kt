package cdu145.tickets.solution.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import cdu145.loadable.Loadable
import cdu145.loadable.Loadable.NotReady
import cdu145.loadable.Loadable.Ready

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