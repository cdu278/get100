package cdu145.tickets.hint

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import cdu145.tickets.AppDatabase
import cdu145.actual.Actual
import cdu145.tickets.ApplicationCoroutineScope
import cdu145.tickets.hint.available.AvailableHintCountFlow
import cdu145.tickets.hint.available.AvailableHints
import cdu145.tickets.hint.available.ReviseAvailableHints
import cdu145.tickets.hint.restoring.ActualRemainingRestorationTime
import cdu145.tickets.hint.restoring.AlarmManagerRestoringHintDeletion
import cdu145.tickets.hint.restoring.RestoringHintDeletion
import cdu145.tickets.solution.correct.CorrectSolutions
import cdu145.tickets.solution.result.SolutionResultFlow
import cdu145.tickets.solution.signs.ActualSolutionSigns
import cdu145.ui.dialogs.DialogState.Hidden

val JustOpenedGapChannel = StringQualifier("OpenedSignPositionChannel")
val NoHintsAvailableDialogState = StringQualifier("NoHintsAvailableDialogStateFlow")
val AlmostCompletedDialogState = StringQualifier("AlmostThereDialogState")
val RemainingRestorationTime = StringQualifier("RemainingRestorationTime")

val HintModule = module {
    single(JustOpenedGapChannel) {
        Channel<Int>(capacity = Channel.CONFLATED)
    } bind SendChannel ::class bind ReceiveChannel::class

    factory { get<AppDatabase>().restoringHintsDao }

    val availableCountFlow = StringQualifier("AvailableHintCountFlow")
    factory(availableCountFlow) { AvailableHintCountFlow(get()) }

    single(NoHintsAvailableDialogState) { MutableStateFlow(Hidden) } bind StateFlow::class
    single(AlmostCompletedDialogState) { MutableStateFlow(Hidden) } bind StateFlow::class

    viewModel {
        HintButtonViewModel(
            get(availableCountFlow),
            get(SolutionResultFlow),
            ActualSuggestedHint(
                get(CorrectSolutions),
                get(ActualSolutionSigns),
                AlmostCompletedDialogImpl(
                    get(AlmostCompletedDialogState),
                ),
                AvailableHints(
                    get(),
                    get(),
                ),
                get(JustOpenedGapChannel),
            ),
            get(NoHintsAvailableDialogState),
        )
    }

    factory<RestoringHintDeletion> { AlarmManagerRestoringHintDeletion(get()) }

    factory<Actual<*>>(RemainingRestorationTime) { ActualRemainingRestorationTime(get()) }

    factory {
        ReviseAvailableHints(
            get(),
            get(),
            get(ApplicationCoroutineScope),
        )
    }
}