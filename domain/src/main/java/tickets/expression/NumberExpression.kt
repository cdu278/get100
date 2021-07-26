package tickets.expression

import tickets.expression.evaluation.operands.EvaluationOperands
import tickets.solution.result.SolutionResult
import tickets.solution.result.value.SolutionResultValue

internal class NumberExpression(
    private val value: Double,
) : Expression {

    override fun impact(evaluationOperands: EvaluationOperands): EvaluationOperands {
        return evaluationOperands.add(value)
    }

    private val Double.isIntegerNumber: Boolean
        get() = this.toInt().toDouble() == this

    override fun asSolutionResult(): SolutionResult {
        return if (value == 100.0) {
            SolutionResult.Solved
        } else {
            SolutionResult.NotSolved(
                SolutionResultValue.Defined(value),
            )
        }
    }
}