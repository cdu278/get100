package tickets.expression

import tickets.expression.evaluation.operands.EvaluationOperands
import tickets.expression.evaluation.operands.OperandsWithUndefined
import tickets.solution.result.SolutionResult
import tickets.solution.result.value.SolutionResultValue.Undefined

internal object UndefinedExpression : Expression {

    override fun impact(evaluationOperands: EvaluationOperands): EvaluationOperands {
        return OperandsWithUndefined
    }

    override fun asSolutionResult(): SolutionResult {
        return SolutionResult.NotSolved(value = Undefined)
    }
}