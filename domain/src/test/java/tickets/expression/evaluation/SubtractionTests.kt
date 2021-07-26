package tickets.expression.evaluation

import com.natpryce.hamkrest.assertion.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import tickets.expression.NumberExpression
import tickets.expression.UndefinedExpression
import tickets.solution.signs.ArithmeticSign

@DisplayName("Subtraction")
internal class SubtractionTests {

    @Test
    fun `With undefined should result undefined`() {
        assertThat(
            ArithmeticEvaluation(ArithmeticSign.MINUS)
                .withOperand(NumberExpression(3.0))
                .withOperand(UndefinedExpression),
            resultsUndefined(),
        )
    }

    @Test
    fun `With defined should work as expected`() {
        assertThat(
            ArithmeticEvaluation(ArithmeticSign.MINUS)
                .withOperand(NumberExpression(3.0))
                .withOperand(NumberExpression(2.0)),
            resultsValueEqualTo(1.0),
        )
    }
}