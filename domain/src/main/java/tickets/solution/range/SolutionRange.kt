package tickets.solution.range

import tickets.digits.position.DigitPosition
import tickets.solution.signs.position.SignPosition

internal interface SolutionRange {

    object Full : SolutionRange by DigitPositionRange(DigitPosition.FIRST, DigitPosition.LAST)

    interface UsePurpose<out R> {

        fun useOneDigitRange(position: DigitPosition): R

        fun useMultipleDigitsRange(
            firstDigitPosition: DigitPosition,
            lastDigitPosition: DigitPosition,
        ): R
    }

    fun <R> useFor(purpose: UsePurpose<R>): R

    fun toRightOf(signPosition: SignPosition): SolutionRange

    fun toLeftOf(signPosition: SignPosition): SolutionRange
}