package tickets.hint

import android.content.Context
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import tickets.actual.DataStoreMutable
import tickets.flow.DataStoreFlow
import tickets.hint.available.AvailableHints
import tickets.hint.available.NoOpHintRestoration
import tickets.hint.available.availableHintCountDataStore
import tickets.solution.correct.CorrectSolutions
import tickets.solution.result.SolutionResultFlow
import tickets.solution.signs.ActualSolutionSigns

val JustOpenedGapChannel = StringQualifier("OpenedSignPositionChannel")

val HintModule = module {
    single(JustOpenedGapChannel) {
        Channel<Int>(capacity = Channel.CONFLATED)
    } bind SendChannel ::class bind ReceiveChannel::class

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
                get(JustOpenedGapChannel),
            ),
            ToastNoHintsAvailableDialog(get()),
        )
    }
}