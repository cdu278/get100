package tickets.expression

import tickets.expression.evaluation.operands.EvaluationOperands
import tickets.solution.result.SolutionResult

internal interface Expression {

    fun impact(evaluationOperands: EvaluationOperands): EvaluationOperands

    fun asSolutionResult(): SolutionResult
}