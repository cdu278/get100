package tickets.solution.gap

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import tickets.actual.Actual
import tickets.solution.signs.position.SignPosition

private const val TAG = "SolutionGapsVM"

interface SolutionGapsViewModel {

    val state: Flow<SolutionGapsState>

    fun highlightGapAt(position: Int)

    object Preview : SolutionGapsViewModel {

        override val state: Flow<SolutionGapsState> = emptyFlow()

        override fun highlightGapAt(position: Int) {
            Log.d(TAG, "Highlighted gap#$position")
        }
    }
}

class SolutionGapsViewModelImpl(
    private val highlightedPosition: Actual.Mutable<SignPosition>,
    private val highlightedPositionFlow: Flow<SignPosition>,
) : ViewModel(), SolutionGapsViewModel {

    private val _state = MutableStateFlow(
        SolutionGapsState(
            enabled = true,
            highlightedPosition = 0,
        )
    )

    override val state: Flow<SolutionGapsState>
        get() = _state

    init {
        viewModelScope.launch {
            highlightedPositionFlow.collect {
                _state.value = _state.value.copy(highlightedPosition = it.value)
            }
        }
    }

    override fun highlightGapAt(position: Int) {
        viewModelScope.launch { highlightedPosition.mutate(SignPosition(position)) }
    }
}