package tickets.expression.evaluation.operands

import tickets.expression.Expression
import tickets.expression.NumberExpression
import tickets.expression.evaluation.operands.transform.EvaluationOperandsTransform

internal interface EvaluationOperands {

    fun add(operand: Double): EvaluationOperands

    fun fold(transform: EvaluationOperandsTransform): Expression

    fun fold(transform: (Double, Double) -> Double): Expression
}

private fun throwIllegalState(): Nothing {
    error("Evaluation operands must contain exactly 2 items")
}

internal object EmptyEvaluationOperands : EvaluationOperands {

    override fun add(operand: Double): EvaluationOperands = OneEvaluationOperand(operand)

    override fun fold(transform: EvaluationOperandsTransform): Expression {
        throwIllegalState()
    }

    override fun fold(transform: (Double, Double) -> Double): Expression {
        throwIllegalState()
    }
}

private class OneEvaluationOperand(
    private val left: Double,
) : EvaluationOperands {

    override fun add(operand: Double): EvaluationOperands {
        return TwoEvaluationOperands(left, right = operand)
    }

    override fun fold(transform: EvaluationOperandsTransform): Expression {
        throwIllegalState()
    }

    override fun fold(transform: (Double, Double) -> Double): Expression {
        throwIllegalState()
    }
}

private class TwoEvaluationOperands(
    private val left: Double,
    private val right: Double,
) : EvaluationOperands {

    override fun add(operand: Double): EvaluationOperands {
        throwIllegalState()
    }

    override fun fold(transform: EvaluationOperandsTransform): Expression {
        return transform(left, right)
    }

    override fun fold(transform: (Double, Double) -> Double): Expression {
        return NumberExpression(transform(left, right))
    }
}