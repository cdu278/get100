package tickets.solution.clear

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tickets.actual.Actual
import tickets.solution.signs.SolutionSigns

interface ClearSolutionButtonViewModel {

    fun clearSolution()

    companion object {

        val Preview = object : ClearSolutionButtonViewModel {

            override fun clearSolution() {
                // No-op
            }
        }
    }
}

class ClearSolutionButtonViewModelImpl(
    private val solutionSigns: Actual.Mutable<SolutionSigns>,
) : ViewModel(), ClearSolutionButtonViewModel {

    override fun clearSolution() {
        viewModelScope.launch { solutionSigns.mutate(SolutionSigns.Empty) }
    }
}