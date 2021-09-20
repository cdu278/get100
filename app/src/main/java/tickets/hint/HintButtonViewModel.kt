package tickets.hint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tickets.actual.Actual
import tickets.hint.AvailableCountState.NotReady
import tickets.hint.AvailableCountState.Ready
import tickets.solution.result.SolutionResult
import tickets.solution.result.value.SolutionResultValue

class HintButtonViewModel(
    availableCountFlow: Flow<Int>,
    solutionResultFlow: Flow<SolutionResult>,
    private val suggestedHint: Actual<Hint>,
    private val noHintsAvailableDialog: NoHintsAvailableDialog,
) : ViewModel() {

    private val _enabled = MutableStateFlow(true)

    val enabled: StateFlow<Boolean>
        get() = _enabled


    private val _availableCountState = MutableStateFlow<AvailableCountState>(NotReady)

    val availableCountState: StateFlow<AvailableCountState>
        get() = _availableCountState

    init {
        solutionResultFlow
            .onEach { _enabled.value = !it.solved }
            .launchIn(viewModelScope)
        availableCountFlow
            .onEach { _availableCountState.value = Ready(it) }
            .launchIn(viewModelScope)
    }

    private val SolutionResult.solved: Boolean
        get() = this.useFor(CheckingSolved)

    private object CheckingSolved : SolutionResult.UsePurpose<Boolean> {

        override fun useSolved(): Boolean = true

        override fun useNotSolved(value: SolutionResultValue): Boolean = false
    }

    interface NoHintsAvailableDialog {

        fun show()
    }

    fun useHint() {
        viewModelScope.launch {
            if ((availableCountState.value as Ready).value > 0) {
                suggestedHint.value().use()
            } else {
                noHintsAvailableDialog.show()
            }
        }
    }
}