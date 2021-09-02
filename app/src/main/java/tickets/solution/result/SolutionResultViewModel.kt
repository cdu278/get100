package tickets.solution.result

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface SolutionResultViewModel {

    val state: Flow<SolutionResultViewState>

    object Preview : SolutionResultViewModel {

        override val state: Flow<SolutionResultViewState> = emptyFlow()
    }
}

class SolutionResultViewModelImpl : ViewModel(),
    SolutionResultViewModel by SolutionResultViewModel.Preview