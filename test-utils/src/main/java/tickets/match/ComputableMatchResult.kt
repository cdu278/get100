package tickets.match

import com.natpryce.hamkrest.MatchResult

interface ComputableMatchResult {

    fun compute(mismatchDescription: (actualResult: String) -> String): MatchResult
}

object ComputableToMatch : ComputableMatchResult {

    override fun compute(mismatchDescription: (actualResult: String) -> String): MatchResult {
        return MatchResult.Match
    }
}

class ComputableToMismatch(
    private val actualResult: String,
) : ComputableMatchResult {

    override fun compute(mismatchDescription: (actualResult: String) -> String): MatchResult {
        return MatchResult.Mismatch(mismatchDescription(actualResult))
    }
}