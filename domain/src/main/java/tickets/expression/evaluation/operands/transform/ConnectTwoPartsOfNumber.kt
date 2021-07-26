package tickets.expression.evaluation.operands.transform

import tickets.expression.Expression
import tickets.expression.NumberExpression
import tickets.math.DecimalOrderOf

internal object ConnectTwoPartsOfNumber : EvaluationOperandsTransform {

    override operator fun invoke(left: Double, right: Double): Expression {
        return NumberExpression(
            DecimalOrderOf(right).shift(left) + right
        )
    }
}