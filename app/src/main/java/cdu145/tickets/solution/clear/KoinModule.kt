package cdu145.tickets.solution.clear

import cdu145.tickets.solution.Solution
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import cdu145.tickets.solution.result.SolutionResultFlow

val ClearSolutionModule = module {
    viewModel {
        ClearSolutionButtonViewModel(
            get(Solution),
            get(SolutionResultFlow),
        )
    }
}