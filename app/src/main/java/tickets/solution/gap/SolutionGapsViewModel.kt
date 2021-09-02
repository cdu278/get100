package tickets.solution.gap

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

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

class SolutionGapsViewModelImpl : ViewModel(),
    SolutionGapsViewModel by SolutionGapsViewModel.Preview