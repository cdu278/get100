package tickets.solution.signs.position

import com.natpryce.hamkrest.MatchResult
import com.natpryce.hamkrest.Matcher

internal fun equivalentTo(given: SignPosition): Matcher<SignPosition> {
    return object : Matcher.Primitive<SignPosition>() {

        override val description: String
            get() = "is ${given.value}"

        override fun invoke(actual: SignPosition): MatchResult {
            return if (actual.compareTo(given) == 0) {
                MatchResult.Match
            } else {
                MatchResult.Mismatch("is ${actual.value}")
            }
        }
    }
}