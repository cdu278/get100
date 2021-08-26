package tickets.flow.matcher

import com.natpryce.hamkrest.MatchResult
import com.natpryce.hamkrest.MatchResult.Match
import com.natpryce.hamkrest.MatchResult.Mismatch
import com.natpryce.hamkrest.Matcher
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

private fun <T> emitsExactly(elementMatchers: List<Matcher<T>>): Matcher<Flow<T>> {
    return object : Matcher.Primitive<Flow<T>>() {

        override val description: String
            get() = "emits " + elementMatchers.joinToString(prefix = "[",
                postfix = "]") { it.description }

        private val indexSequence: Sequence<Int>
            get() = generateSequence(seed = 0) { it + 1 }

        override fun invoke(actual: Flow<T>): MatchResult {
            return runBlocking {
                actual
                    .toList(mutableListOf())
                    .let { elements ->
                        val actualCount = elements.count()
                        val expectedCount = elementMatchers.count()
                        if (actualCount != expectedCount) {
                            return@runBlocking Mismatch(
                                "emits $actualCount elements instead of $expectedCount",
                            )
                        } else {
                            elements
                        }
                    }
                    .asSequence()
                    .zip(indexSequence)
                    .zip(elementMatchers.asSequence()) { (element, index), matcher ->
                        Pair(index, matcher.invoke(element))
                    }
                    .find { (_, matchResult) -> matchResult is Mismatch }
                    ?.let { (index, matchResult) ->
                        matchResult as Mismatch
                        Mismatch("element#${index + 1} ${matchResult.description}")
                    }
                    ?: Match
            }
        }
    }
}

internal fun <T> emitsExactly(vararg elementMatchers: Matcher<T>): Matcher<Flow<T>> {
    return emitsExactly(elementMatchers.asList())
}

fun main() {
    assertThat(
        flowOf(2, 2, 3),
        emitsExactly(equalTo(1), equalTo(2), equalTo(3)),
    )
}