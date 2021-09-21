package tickets.hint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tickets.actual.Actual
import tickets.hint.AvailableCountState.NotReady
import tickets.hint.AvailableCountState.Ready
import tickets.solution.result.SolutionResult
import tickets.solution.result.isHundred

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
            .onEach { _enabled.value = !it.isHundred }
            .launchIn(viewModelScope)
        availableCountFlow
            .onEach { _availableCountState.value = Ready(it) }
            .launchIn(viewModelScope)
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