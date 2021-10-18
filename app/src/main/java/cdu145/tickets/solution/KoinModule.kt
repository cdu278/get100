package cdu145.tickets.solution

import cdu145.tickets.ApplicationCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

val AllPossibleSolutions = StringQualifier("AllPossibleSolutions")

val SolutionModule = module {
    single(AllPossibleSolutions) {
        get<CoroutineScope>(ApplicationCoroutineScope).async(Dispatchers.Default) {
            allPossibleSolutions()
        }
    }
}