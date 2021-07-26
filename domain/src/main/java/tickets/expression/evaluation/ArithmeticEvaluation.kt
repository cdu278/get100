package tickets.expression.evaluation

import tickets.expression.Expression
import tickets.expression.evaluation.operands.EmptyEvaluationOperands
import tickets.expression.evaluation.operands.EvaluationOperands
import tickets.expression.evaluation.operands.transform.SafeDivide
import tickets.expression.evaluation.operands.transform.ConnectTwoPartsOfNumber
import tickets.solution.signs.ArithmeticSign
import tickets.solution.signs.SolutionSign

internal class ArithmeticEvaluation private constructor(
    private val sign: SolutionSign,
    private val operands: EvaluationOperands,
) : ExpressionEvaluation {

    constructor(sign: SolutionSign) : this(sign, EmptyEvaluationOperands)

    override fun withOperand(expression: Expression): ExpressionEvaluation {
        return ArithmeticEvaluation(sign, expression.impact(operands))
    }

    override fun evaluate(): Expression {
        return when (sign.value) {
            ArithmeticSign.PLUS -> operands.fold(transform = Double::plus)
            ArithmeticSign.MINUS -> operands.fold(transform = Double::minus)
            ArithmeticSign.TIMES -> operands.fold(transform = Double::times)
            ArithmeticSign.DIV -> operands.fold(transform = SafeDivide)
            ArithmeticSign.NONE -> operands.fold(transform = ConnectTwoPartsOfNumber)
        }
    }
}