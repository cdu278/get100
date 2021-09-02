package tickets.solution.clear

import androidx.lifecycle.ViewModel

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

class ClearSolutionButtonViewModelImpl : ViewModel(),
    ClearSolutionButtonViewModel by ClearSolutionButtonViewModel.Preview