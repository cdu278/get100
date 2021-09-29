package tickets.hint

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import tickets.AppDatabase
import tickets.actual.Actual
import tickets.coroutine.scope.ApplicationCoroutineScope
import tickets.hint.available.AvailableHintCountFlow
import tickets.hint.available.AvailableHints
import tickets.hint.restoring.ActualRemainingRestorationTime
import tickets.hint.restoring.CleanUpRestoringHints
import tickets.solution.correct.CorrectSolutions
import tickets.solution.result.SolutionResultFlow
import tickets.solution.signs.ActualSolutionSigns
import tickets.ui.state.DialogState.Hidden

val JustOpenedGapChannel = StringQualifier("OpenedSignPositionChannel")
val NoHintsAvailableDialogState = StringQualifier("NoHintsAvailableDialogStateFlow")
val RemainingRestorationTime = StringQualifier("RemainingRestorationTime")

val HintModule = module {
    single(JustOpenedGapChannel) {
        Channel<Int>(capacity = Channel.CONFLATED)
    } bind SendChannel ::class bind ReceiveChannel::class

    factory { get<AppDatabase>().restoringHintsDao }

    val availableCountFlow = StringQualifier("AvailableHintCountFlow")
    factory(availableCountFlow) { AvailableHintCountFlow(get()) }

    single(NoHintsAvailableDialogState) { MutableStateFlow(Hidden) } bind StateFlow::class

    viewModel {
        HintButtonViewModel(
            get(availableCountFlow),
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
            get(NoHintsAvailableDialogState),
        )
    }

    factory {
        CleanUpRestoringHints(
            get(),
            get(ApplicationCoroutineScope),
        )
    }

    factory<Actual<*>>(RemainingRestorationTime) { ActualRemainingRestorationTime(get()) }
}