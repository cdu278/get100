package tickets.solution.chain

import com.natpryce.hamkrest.assertion.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import tickets.digits.FragmentOfTicketDigits
import tickets.digits.TicketDigits
import tickets.solution.signs.ArithmeticSign.*
import tickets.solution.signs.FragmentOfSolutionSigns
import tickets.solution.signs.SolutionSigns

@Suppress("ClassName")
internal class SolutionChainTests {

    @Nested
    inner class `Chains containing division by 0 should be folded to undefined` {

        @Test
        fun `Case 1 - direct division by 0`() {
            assertThat(
                FragmentOfSolutionChain(0, DIV, 0),
                foldedToUndefined(),
            )
        }

        @Test
        fun `Case 2 - indirect division by 0`() {
            assertThat(
                FragmentOfSolutionChain(0, DIV, 0, NONE, 0),
                foldedToUndefined(),
            )
        }
    }

    @Test
    fun `Chains not containing division by 0 should be normally folded to a value`() {
        assertThat(
            FullSolutionChain(
                FragmentOfTicketDigits(5, 2, 7, 7, 0, 9),
                FragmentOfSolutionSigns(TIMES, PLUS, DIV, MINUS, NONE),
            ),
            foldedToValueEqualTo(2.0),
        )
    }

    @Test
    fun `Chains with lowest priority sign at the end should be folded normally`() {
        assertThat(
            FullSolutionChain(
                TicketDigits(0, 0, 0, 0, 1, 2),
                SolutionSigns(NONE, NONE, NONE, NONE, PLUS),
            ),
            foldedToValueEqualTo(3.0),
        )
    }

    @Test
    fun `Chains with lowest priority sign at the beginning should be folded normally`() {
        assertThat(
            FullSolutionChain(
                TicketDigits(1, 2, 0, 0, 0, 0),
                SolutionSigns(PLUS, NONE, NONE, NONE, NONE),
            ),
            foldedToValueEqualTo(20001.0),
        )
    }
}