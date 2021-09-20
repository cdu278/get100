package tickets.hint

import android.content.Context
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tickets.actual.DataStoreMutable
import tickets.flow.DataStoreFlow
import tickets.hint.available.AvailableHints
import tickets.hint.available.NoOpHintRestoration
import tickets.hint.available.availableHintCountDataStore
import tickets.solution.correct.CorrectSolutions
import tickets.solution.result.SolutionResultFlow
import tickets.solution.signs.ActualSolutionSigns

val HintModule = module {
    viewModel {
        HintButtonViewModel(
            availableCountFlow = DataStoreFlow(get<Context>().availableHintCountDataStore),
            get(SolutionResultFlow),
            ActualSuggestedHint(
                get(CorrectSolutions),
                get(ActualSolutionSigns),
                ToastAlmostThereDialog(get()),
                AvailableHints(
                    count = DataStoreMutable(get<Context>().availableHintCountDataStore),
                    NoOpHintRestoration,
                ),
            ),
            ToastNoHintsAvailableDialog(get()),
        )
    }
}