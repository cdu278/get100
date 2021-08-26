package tickets.flowable.matcher

import com.natpryce.hamkrest.MatchResult
import com.natpryce.hamkrest.MatchResult.Match
import com.natpryce.hamkrest.MatchResult.Mismatch
import com.natpryce.hamkrest.Matcher
import kotlinx.coroutines.flow.Flow
import tickets.flowable.Flowable

internal fun <T> providesFlowThat(flowMatcher: Matcher<Flow<T>>): Matcher<Flowable<T>> {
    return object : Matcher.Primitive<Flowable<T>>() {

        override val description: String
            get() = "provides flow that ${flowMatcher.description}"

        override fun invoke(actual: Flowable<T>): MatchResult {
            return when (val flowMatchResult = flowMatcher.invoke(actual.flow)) {
                is Match -> Match
                is Mismatch -> Mismatch("provided flow ${flowMatchResult.description}")
            }
        }
    }
}