package cdu145.tickets.hint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import cdu145.actual.Actual
import cdu145.loadable.Loadable
import cdu145.loadable.Loadable.NotReady
import cdu145.loadable.Loadable.Ready
import cdu145.tickets.solution.result.SolutionResult
import cdu145.tickets.solution.result.isHundred
import cdu145.ui.dialogs.DialogState

class HintButtonViewModel(
    availableCountFlow: Flow<Int>,
    solutionResultFlow: Flow<SolutionResult>,
    private val suggestedHint: Actual<Hint>,
    private val noHintsAvailableDialogState: MutableStateFlow<DialogState>,
) : ViewModel() {

    private val _enabled = MutableStateFlow(true)

    val enabled: StateFlow<Boolean>
        get() = _enabled


    private val _availableCount = MutableStateFlow<Loadable<Int>>(NotReady)

    val availableCount: StateFlow<Loadable<Int>>
        get() = _availableCount

    init {
        solutionResultFlow
            .onEach { _enabled.value = !it.isHundred }
            .launchIn(viewModelScope)
        availableCountFlow
            .onEach { _availableCount.value = Ready(it) }
            .launchIn(viewModelScope)
    }

    fun useHint() {
        if (!enabled.value) return

        if ((availableCount.value as Ready).value > 0) {
            viewModelScope.launch { suggestedHint.value().use() }
        } else {
            noHintsAvailableDialogState.value = DialogState.Shown
        }
    }
}