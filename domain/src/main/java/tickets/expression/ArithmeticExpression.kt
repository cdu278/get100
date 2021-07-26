package tickets.expression

import tickets.expression.evaluation.ArithmeticEvaluation
import tickets.expression.evaluation.operands.EvaluationOperands
import tickets.solution.result.SolutionResult
import tickets.solution.signs.SolutionSign

internal class ArithmeticExpression(
    private val sign: SolutionSign,
    private val left: Expression,
    private val right: Expression,
) : Expression {

    private val evaluated: Expression
        get() = ArithmeticEvaluation(sign).withOperand(left).withOperand(right).evaluate()

    override fun impact(evaluationOperands: EvaluationOperands): EvaluationOperands {
        return evaluated.impact(evaluationOperands)
    }

    override fun asSolutionResult(): SolutionResult = evaluated.asSolutionResult()
}