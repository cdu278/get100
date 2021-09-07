package tickets.solution.clear

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tickets.solution.signs.ActualSolutionSigns

val ClearSolutionModule = module {
    viewModel {
        ClearSolutionButtonViewModelImpl(get(ActualSolutionSigns))
    }
}