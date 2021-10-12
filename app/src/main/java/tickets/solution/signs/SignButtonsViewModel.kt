package tickets.solution.signs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tickets.actual.Actual
import tickets.solution.result.SolutionResult
import tickets.solution.result.isHundred

interface SignButtonsViewModel {

    val shown: StateFlow<Boolean>

    fun chooseSign(sign: SolutionSign)

    companion object {

        val Preview = object : SignButtonsViewModel {

            override val shown: StateFlow<Boolean> = MutableStateFlow(false)

            override fun chooseSign(sign: SolutionSign) {
                // No-op
            }
        }
    }
}

class SignButtonsViewModelImpl(
    private val solutionSigns: Actual.Mutable<SolutionSigns>,
    private val highlightedSignPosition: Actual<Int>,
    private val solutionResultFlow: Flow<SolutionResult>,
) : ViewModel(), SignButtonsViewModel {

    private val _shown = MutableStateFlow(value = true)

    override val shown: StateFlow<Boolean>
        get() = _shown

    init {
        viewModelScope.launch {
            solutionResultFlow.collect { _shown.value = !it.isHundred }
        }
    }

    override fun chooseSign(sign: SolutionSign) {
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