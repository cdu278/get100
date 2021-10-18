package cdu145.tickets.solution.signs.permutations

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.plus
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import cdu145.coroutine.scope.ApplicationCoroutineScope

val CachedSignsPermutations = StringQualifier("CachedSignsPermutations")

val SignsPermutationsModule = module {
    single(CachedSignsPermutations) {
        AllSignsPermutations.cached(
            scope = get<CoroutineScope>(ApplicationCoroutineScope) + Dispatchers.Default,
        )
    }
}