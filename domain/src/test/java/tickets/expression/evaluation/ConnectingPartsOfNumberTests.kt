package tickets.expression.evaluation

import com.natpryce.hamkrest.assertion.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import tickets.expression.NumberExpression
import tickets.expression.UndefinedExpression
import tickets.solution.signs.ArithmeticSign

@DisplayName("Connecting parts of number")
internal class ConnectingPartsOfNumberTests {

    @Test
    fun `When one part is undefined should result undefined`() {
        assertThat(
            ArithmeticEvaluation(ArithmeticSign.NONE)
                .withOperand(NumberExpression(3.0))
                .withOperand(UndefinedExpression),
            resultsUndefined(),
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 1.0, 23.0, 456.0])
    fun `Connecting 0 to any number should result this number`(rightNumber: Double) {
        assertThat(
            ArithmeticEvaluation(ArithmeticSign.NONE)
                .withOperand(NumberExpression(0.0))
                .withOperand(NumberExpression(rightNumber)),
            resultsValueEqualTo(rightNumber)
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [0.0, 9.0])
    fun `Connecting 2 to a from 0 to 9 should result (2 x 10 + a)`(a: Double) {
        assertThat(
            ArithmeticEvaluation(ArithmeticSign.NONE)
                .withOperand(NumberExpression(2.0))
                .withOperand(NumberExpression(a)),
            resultsValueEqualTo(2 * 10 + a),
        )
    }

    @ParameterizedTest
    @ValueSource(doubles = [10.0, 19.0])
    fun `Connecting 2 to a from 10 to 19 should result (2 x 100 + a)`(a: Double) {
        assertThat(
            ArithmeticEvaluation(ArithmeticSign.NONE)
                .withOperand(NumberExpression(2.0))
                .withOperand(NumberExpression(a)),
            resultsValueEqualTo(2 * 100 + a),
        )
    }
}