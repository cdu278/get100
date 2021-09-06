package tickets.solution.gap

import android.content.Context
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module
import tickets.actual.Actual
import tickets.actual.DataStoreMutable
import tickets.flow.DataStoreFlow
import tickets.solution.signs.position.SignPosition

val HighlightedSignPositionDataStore = StringQualifier("HighlightedSignPositionDataStore")
val ActualHighlightedSignPosition = StringQualifier("ActualHighlightedSignPosition")
val HighlightedSignPositionFlow = StringQualifier("HighlightedSignPositionFlow")

val SolutionGapsModule = module {
    factory(HighlightedSignPositionDataStore) { get<Context>().highlightedGapDataStore }
    factory<Actual<SignPosition>>(ActualHighlightedSignPosition) {
        DataStoreMutable(get(HighlightedSignPositionDataStore))
    } bind Actual.Mutable::class
    factory<Flow<SignPosition>>(HighlightedSignPositionFlow) {
        DataStoreFlow(get(HighlightedSignPositionDataStore))
    }
    viewModel {
        SolutionGapsViewModelImpl(
            highlightedPosition = get(ActualHighlightedSignPosition),
            highlightedPositionFlow = get(HighlightedSignPositionFlow),
        )
    }
}