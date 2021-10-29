package cdu145.tickets.solution.clear

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import cdu145.actual.Actual
import cdu145.tickets.solution.result.SolutionResult
import cdu145.tickets.solution.result.isHundred
import cdu145.tickets.solution.Solution

class ClearSolutionButtonViewModel(
    private val solution: Actual.Mutable<Solution>,
    private val solutionResultFlow: Flow<SolutionResult>,
) : ViewModel() {

    private val _shown = MutableStateFlow(true)

    val shown: StateFlow<Boolean>
        get() = _shown

    init {
        solutionResultFlow
            .onEach { _shown.value = !it.isHundred }
            .launchIn(viewModelScope)
    }

    fun clearSolution() {
        if (!shown.value) return

        viewModelScope.launch { solution.mutate(cdu145.tickets.solution.Solution.Empty) }
    }
}