package cdu145.tickets.solution

import android.content.Context
import cdu145.actual.Actual
import cdu145.actual.DataStoreActual
import cdu145.flow.DataStoreFlow
import cdu145.tickets.ApplicationCoroutineScope
import cdu145.tickets.solution.signs.solutionSignsDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module

val Solution = StringQualifier("Solution")
val SolutionFlow = StringQualifier("SolutionFlow")

val AllPossibleSolutions = StringQualifier("AllPossibleSolutions")

val SolutionModule = module {
    factory<Actual<Solution>>(Solution) {
        DataStoreActual(get<Context>().solutionSignsDataStore)
    } bind Actual.Mutable::class

    factory(SolutionFlow) {
        DataStoreFlow(get<Context>().solutionSignsDataStore)
    }

    single(AllPossibleSolutions) {
        get<CoroutineScope>(ApplicationCoroutineScope).async(Dispatchers.Default) {
            allPossibleSolutions()
        }
    }
}