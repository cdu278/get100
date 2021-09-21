package tickets.solution.result

import tickets.solution.result.SolutionResult.Defined
import tickets.solution.result.SolutionResult.Undefined
import tickets.solution.signs.ArithmeticSign
import tickets.solution.signs.SolutionSign

internal fun arithmeticOperationResult(
        sign: SolutionSign,
        left: SolutionResult,
        right: SolutionResult,
): SolutionResult {
    return if (left is Defined && right is Defined) {
        sign.performOperation(left.value, right.value)
    } else {
        Undefined
    }
}

private fun SolutionSign.performOperation(left: Double, right: Double): SolutionResult {
    return when (this.value) {
        ArithmeticSign.PLUS -> Defined(left + right)
        ArithmeticSign.MINUS -> Defined(left - right)
        ArithmeticSign.TIMES -> Defined(left * right)
        ArithmeticSign.DIV -> left.safelyDividedBy(right)
        ArithmeticSign.NONE -> Defined(numberOfTwoParts(left, right))
    }
}

private fun Double.safelyDividedBy(divider: Double): SolutionResult {
    return if (divider == 0.0) Undefined else Defined(this / divider)
}

private fun numberOfTwoParts(left: Double, right: Double): Double {
    return (left * right.decimalShift) + right
}

private val Double.decimalShift: Int
    get() {
        var value = 10
        while (value <= this) {
            value *= 10
        }
        return value
    }