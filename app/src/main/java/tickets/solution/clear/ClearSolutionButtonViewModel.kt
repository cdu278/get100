package tickets.solution.clear

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tickets.actual.Actual
import tickets.solution.signs.SolutionSigns

class ClearSolutionButtonViewModel(
    private val solutionSigns: Actual.Mutable<SolutionSigns>,
) : ViewModel() {

    fun clearSolution() {
        viewModelScope.launch { solutionSigns.mutate(SolutionSigns.Empty) }
    }
}