package tickets.solution.result

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test
import tickets.solution.result.SolutionResult.Defined
import tickets.solution.result.SolutionResult.Undefined
import tickets.solution.signs.SolutionSign.*

internal class ArithmeticOperationResultTests {

    @Test
    fun `When left operand is undefined should be undefined`() {
        assertThat(
                arithmeticOperationResult(
                        NONE,
                        left = Undefined,
                        right = Defined(0.0),
                ),
                equalTo(Undefined),
        )
    }

    @Test
    fun `When right operand is undefined should be undefined`() {
        assertThat(
                arithmeticOperationResult(
                        NONE,
                        left = Defined(0.0),
                        right = Undefined,
                ),
                equalTo(Undefined),
        )
    }

    @Test
    fun `Addition should be evaluated normally`() {
        assertThat(
                arithmeticOperationResult(
                        sign = PLUS,
                        left = Defined(3.0),
                        right = Defined(2.0),
                ),
                equalTo(Defined(5.0)),
        )
    }

    @Test
    fun `Subtraction should be evaluated normally`() {
        assertThat(
                arithmeticOperationResult(
                        sign = MINUS,
                        left = Defined(3.0),
                        right = Defined(2.0),
                ),
                equalTo(Defined(1.0)),
        )
    }

    @Test
    fun `Multiplication should be evaluated normally`() {
        assertThat(
                arithmeticOperationResult(
                        sign = TIMES,
                        left = Defined(3.0),
                        right = Defined(2.0),
                ),
                equalTo(Defined(6.0)),
        )
    }

    @Test
    fun `Division by 0 should be undefined`() {
        assertThat(
                arithmeticOperationResult(
                        sign = DIV,
                        left = Defined(1.0),
                        right = Defined(0.0),
                ),
                equalTo(Undefined),
        )
    }

    @Test
    fun `Division by non-zero should be evaluated normally`() {
        assertThat(
                arithmeticOperationResult(
                        sign = DIV,
                        left = Defined(6.3),
                        right = Defined(2.1),
                ),
                equalTo(Defined(3.0)),
        )
    }

    @Test
    fun `Operation with 'NONE' sign should be evaluated by creating one number of two`() {
        assertThat(
                arithmeticOperationResult(
                        sign = NONE,
                        left = Defined(1.0),
                        right = Defined(23.0),
                ),
                equalTo(Defined(123.0)),
        )
    }
}