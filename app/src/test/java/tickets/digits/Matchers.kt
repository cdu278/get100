package tickets.digits

import com.natpryce.hamkrest.MatchResult
import com.natpryce.hamkrest.MatchResult.Match
import com.natpryce.hamkrest.MatchResult.Mismatch
import com.natpryce.hamkrest.Matcher

private fun TicketDigits.asList(): List<Int> = List(size = 6) { i -> this[i].value }

internal fun equivalentTo(digits: TicketDigits): Matcher<TicketDigits> {
    return object : Matcher.Primitive<TicketDigits>() {

        override val description: String
            get() = "equivalent to ${digits.asList()}"

        override fun invoke(actual: TicketDigits): MatchResult {
            actual
                .asList()
                .zip(digits.asList())
                .forEachIndexed { i, (actualValue, expectedValue) ->
                    if (actualValue != expectedValue) {
                        return Mismatch("digit#${i + 1} is $actualValue")
                    }
                }
            return Match
        }
    }
}