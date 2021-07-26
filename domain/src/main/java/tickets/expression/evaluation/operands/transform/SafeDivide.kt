package tickets.expression.evaluation.operands.transform

import tickets.expression.Expression
import tickets.expression.NumberExpression
import tickets.expression.UndefinedExpression

internal object SafeDivide : EvaluationOperandsTransform {

    override operator fun invoke(left: Double, right: Double): Expression {
        return if (right == 0.0) {
            UndefinedExpression
        } else {
            NumberExpression(left / right)
        }
    }
}