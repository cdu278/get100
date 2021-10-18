package cdu145.tickets.solution.signs.permutations

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

fun SolutionsSignsPermutations.cached(scope: CoroutineScope): Deferred<SolutionsSignsPermutations> {
    return scope.async { toList().asSequence() }
}