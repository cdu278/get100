package tickets.solution.signs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tickets.actual.Actual
import tickets.solution.result.SolutionResult
import tickets.solution.result.isHundred

class SignButtonsViewModel(
    private val solutionSigns: Actual.Mutable<SolutionSigns>,
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
            solutionSigns.mutate(
                AlteredSolutionSigns(
                    original = solutionSigns.value(),
                    targetPosition = highlightedSignPosition.value(),
                    newSign = sign,
                ),
            )
        }
    }
}