package cdu145.tickets.solution.clear

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import cdu145.tickets.solution.result.SolutionResultFlow
import cdu145.tickets.solution.signs.ActualSolutionSigns

val ClearSolutionModule = module {
    viewModel {
        ClearSolutionButtonViewModel(
            get(ActualSolutionSigns),
            get(SolutionResultFlow),
        )
    }
}