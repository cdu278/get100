package tickets.hint

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import tickets.AppDatabase
import tickets.coroutine.scope.ApplicationCoroutineScope
import tickets.hint.available.AvailableHintCountFlow
import tickets.hint.available.AvailableHints
import tickets.hint.restoring.CleanUpRestoringHints
import tickets.solution.correct.CorrectSolutions
import tickets.solution.result.SolutionResultFlow
import tickets.solution.signs.ActualSolutionSigns

val JustOpenedGapChannel = StringQualifier("OpenedSignPositionChannel")

val HintModule = module {
    single(JustOpenedGapChannel) {
        Channel<Int>(capacity = Channel.CONFLATED)
    } bind SendChannel ::class bind ReceiveChannel::class

    factory { get<AppDatabase>().restoringHintsDao }

    viewModel {
        HintButtonViewModel(
            AvailableHintCountFlow(
                get(),
            ),
            get(SolutionResultFlow),
            ActualSuggestedHint(
                get(CorrectSolutions),
                get(ActualSolutionSigns),
                ToastAlmostThereDialog(
                    get(),
                ),
                AvailableHints(
                    get(),
                    get(ApplicationCoroutineScope),
                ),
                get(JustOpenedGapChannel),
            ),
            ToastNoHintsAvailableDialog(get()),
        )
    }

    factory {
        CleanUpRestoringHints(
            get(),
            get(ApplicationCoroutineScope),
        )
    }
}