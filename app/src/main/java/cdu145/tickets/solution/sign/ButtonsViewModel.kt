package cdu145.tickets.solution.sign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import cdu145.actual.Actual
import cdu145.tickets.solution.AlteredSolution
import cdu145.tickets.solution.Solution
import cdu145.tickets.solution.result.SolutionResult
import cdu145.tickets.solution.result.isHundred

class SignButtonsViewModel(
    private val solution: Actual.Mutable<Solution>,
    private val highlightedSignPosition: Actual<Int>,
    private val solutionResultFlow: Flow<SolutionResult>,
) : ViewModel() {

    private val _shown = MutableStateFlow(value = true)

    val shown: StateFlow<Boolean>
        get() = _shown

    init {
        viewModelScope.launch {
            solutionResultFlow.collect { _shown.value = !it.isHundred }
        }
    }

    fun chooseSign(sign: SolutionSign) {
        if (!shown.value) return

        viewModelScope.launch {
            solution.mutate(
                AlteredSolution(
                    original = solution.value(),
                    targetPosition = highlightedSignPosition.value(),
                    newSign = sign,
                ),
            )
        }
    }
}