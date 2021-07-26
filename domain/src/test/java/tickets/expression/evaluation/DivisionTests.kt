package tickets.expression.evaluation

import com.natpryce.hamkrest.assertion.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import tickets.expression.NumberExpression
import tickets.expression.UndefinedExpression
import tickets.solution.signs.ArithmeticSign

@DisplayName("Division")
internal class DivisionTests {

    @Test
    fun `With undefined should result undefined`() {
        assertThat(
            ArithmeticEvaluation(ArithmeticSign.DIV)
                .withOperand(NumberExpression(3.0))
                .withOperand(UndefinedExpression),
            resultsUndefined(),
        )
    }

    @Test
    fun `By zero should result undefined`() {
        assertThat(
            ArithmeticEvaluation(ArithmeticSign.DIV)
                .withOperand(NumberExpression(3.0))
                .withOperand(NumberExpression(0.0)),
            resultsUndefined(),
        )
    }

    @Test
    fun `By non-zero should work as expected`() {
        assertThat(
            ArithmeticEvaluation(ArithmeticSign.DIV)
                .withOperand(NumberExpression(6.0))
                .withOperand(NumberExpression(2.0)),
            resultsValueEqualTo(3.0),
        )
    }
}