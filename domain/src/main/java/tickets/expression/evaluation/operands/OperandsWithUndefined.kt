package tickets.expression.evaluation.operands

import tickets.expression.Expression
import tickets.expression.UndefinedExpression
import tickets.expression.evaluation.operands.transform.EvaluationOperandsTransform

internal object OperandsWithUndefined : EvaluationOperands {

    override fun add(operand: Double): EvaluationOperands = this

    override fun fold(transform: EvaluationOperandsTransform): Expression = UndefinedExpression

    override fun fold(transform: (Double, Double) -> Double): Expression = UndefinedExpression
}