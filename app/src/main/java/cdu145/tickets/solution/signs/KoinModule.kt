package cdu145.tickets.solution.signs

import cdu145.tickets.solution.Solution
import cdu145.tickets.solution.gaps.HighlightedGapPosition
import cdu145.tickets.solution.result.SolutionResultFlow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val SignButtonsModule = module {
    viewModel {
        SignButtonsViewModel(
            get(Solution),
            get(HighlightedGapPosition),
            get(SolutionResultFlow),
        )
    }
}