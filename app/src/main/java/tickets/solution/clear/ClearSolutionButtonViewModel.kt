package tickets.solution.clear

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tickets.actual.Actual
import tickets.solution.result.SolutionResult
import tickets.solution.result.isHundred
import tickets.solution.signs.SolutionSigns

class ClearSolutionButtonViewModel(
    private val solutionSigns: Actual.Mutable<SolutionSigns>,
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
        viewModelScope.launch { solutionSigns.mutate(SolutionSigns.Empty) }
    }
}