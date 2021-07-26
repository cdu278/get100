package tickets.expression.evaluation.operands.transform

import tickets.expression.Expression

internal interface EvaluationOperandsTransform {

    operator fun invoke(left: Double, right: Double): Expression
}