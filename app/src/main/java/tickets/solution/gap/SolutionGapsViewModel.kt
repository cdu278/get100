package tickets.solution.gap

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tickets.actual.Actual
import tickets.solution.signs.position.SignPosition

private const val TAG = "SolutionGapsVM"

interface SolutionGapsViewModel {

    val enabled: Flow<Boolean>

    fun highlightGapAt(position: Int)

    object Preview : SolutionGapsViewModel {

        override val enabled: Flow<Boolean> = emptyFlow()

        override fun highlightGapAt(position: Int) {
            Log.d(TAG, "Highlighted gap#$position")
        }
    }
}

class SolutionGapsViewModelImpl(
    private val highlightedPosition: Actual.Mutable<SignPosition>,
) : ViewModel(), SolutionGapsViewModel {

    override val enabled: Flow<Boolean> = flowOf(true)

    override fun highlightGapAt(position: Int) {
        viewModelScope.launch { highlightedPosition.mutate(SignPosition(position)) }
    }
}