package tickets.expression.evaluation

import tickets.expression.Expression

internal interface ExpressionEvaluation {

    fun withOperand(expression: Expression): ExpressionEvaluation

    fun evaluate(): Expression
}