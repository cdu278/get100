package tickets.solution.gap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tickets.actual.Actual
import tickets.solution.Solution
import tickets.solution.gap.GapPosition.None
import tickets.solution.gap.GapPosition.Some
import tickets.solution.gap.ShownSolutionState.NotReady
import tickets.solution.gap.ShownSolutionState.Ready
import tickets.solution.result.SolutionResult
import tickets.solution.result.isHundred
import tickets.solution.signs.position.SignPosition

class SolutionGapsViewModel(
    private val actualHighlightedPosition: Actual.Mutable<SignPosition>,
    highlightedPositionFlow: Flow<SignPosition>,
    solutionFlow: Flow<Solution>,
    solutionResultFlow: Flow<SolutionResult>,
    justOpenedPositionFlow: Flow<Int>,
) : ViewModel() {

    private val _highlightedPosition = MutableStateFlow<GapPosition>(None)

    val highlightedPosition: StateFlow<GapPosition>
        get() = _highlightedPosition

    private val _shownSolutionState = MutableStateFlow<ShownSolutionState>(NotReady)

    val shownSolutionState: StateFlow<ShownSolutionState>
        get() = _shownSolutionState

    private val _enabled = MutableStateFlow(true)

    val enabled: StateFlow<Boolean>
        get() = _enabled

    private val _justOpenedPosition = MutableStateFlow<GapPosition>(None)

    val justOpenedPosition: StateFlow<GapPosition>
        get() = _justOpenedPosition

    init {
        highlightedPositionFlow
            .onEach { _highlightedPosition.value = Some(it.value) }
            .launchIn(viewModelScope)
        solutionFlow
            .onEach { _shownSolutionState.value = Ready(it) }
            .launchIn(viewModelScope)
        solutionResultFlow
            .onEach { _enabled.value = !it.isHundred }
            .launchIn(viewModelScope)
        justOpenedPositionFlow
            .onEach { _justOpenedPosition.value = Some(it) }
            .launchIn(viewModelScope)
    }

    fun highlightGapAt(position: Int) {
        viewModelScope.launch { actualHighlightedPosition.mutate(SignPosition(position)) }
    }
}