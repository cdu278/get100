package tickets.solution.result

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test
import tickets.solution.signs.SolutionSign.*

internal class ArithmeticOperationResultTests {

    @Test
    fun `When left operand is undefined should be undefined`() {
        assertThat(
                arithmeticOperationResultOrNull(
                        NONE,
                        left = null,
                        right = 0.0,
                ),
                equalTo(null),
        )
    }

    @Test
    fun `When right operand is undefined should be undefined`() {
        assertThat(
                arithmeticOperationResultOrNull(
                        NONE,
                        left = 0.0,
                        right = null,
                ),
                equalTo(null),
        )
    }

    @Test
    fun `Addition should be evaluated normally`() {
        assertThat(
                arithmeticOperationResultOrNull(
                        sign = PLUS,
                        left = 3.0,
                        right = 2.0,
                ),
                equalTo(5.0),
        )
    }

    @Test
    fun `Subtraction should be evaluated normally`() {
        assertThat(
                arithmeticOperationResultOrNull(
                        sign = MINUS,
                        left = 3.0,
                        right = 2.0,
                ),
                equalTo(1.0),
        )
    }

    @Test
    fun `Multiplication should be evaluated normally`() {
        assertThat(
                arithmeticOperationResultOrNull(
                        sign = TIMES,
                        left = 3.0,
                        right = 2.0,
                ),
                equalTo(6.0),
        )
    }

    @Test
    fun `Division by 0 should be undefined`() {
        assertThat(
                arithmeticOperationResultOrNull(
                        sign = DIV,
                        left = 1.0,
                        right = 0.0,
                ),
                equalTo(null),
        )
    }

    @Test
    fun `Division by non-zero should be evaluated normally`() {
        assertThat(
                arithmeticOperationResultOrNull(
                        sign = DIV,
                        left = 6.3,
                        right = 2.1,
                ),
                equalTo(3.0),
        )
    }

    @Test
    fun `Operation with 'NONE' sign should be evaluated by creating one number of two`() {
        assertThat(
                arithmeticOperationResultOrNull(
                        sign = NONE,
                        left = 1.0,
                        right = 23.0,
                ),
                equalTo(123.0),
        )
    }
}