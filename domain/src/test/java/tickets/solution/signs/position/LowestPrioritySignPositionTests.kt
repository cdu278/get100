package tickets.solution.signs.position

import com.natpryce.hamkrest.assertion.assertThat
import org.junit.jupiter.api.Test
import tickets.solution.signs.ArithmeticSign.*
import tickets.solution.signs.FragmentOfSolutionSigns

internal class LowestPrioritySignPositionTests {

    @Test
    fun `Should select from the given range only`() {
        assertThat(
            LowestPrioritySignPosition(
                FragmentOfSolutionSigns(PLUS, TIMES),
                from = IntSignPosition(1),
                to = IntSignPosition(1),
            ),
            equivalentTo(IntSignPosition(1)),
        )
    }

    @Test
    fun `From signs of equal priority should select first one`() {
        assertThat(
            LowestPrioritySignPosition(
                FragmentOfSolutionSigns(PLUS, MINUS),
                from = IntSignPosition(0),
                to = IntSignPosition(1),
            ),
            equivalentTo(IntSignPosition(0)),
        )
    }

    @Test
    fun `From signs of non-equal priority should select one having lower priority`() {
        assertThat(
            LowestPrioritySignPosition(
                FragmentOfSolutionSigns(NONE, TIMES),
                from = IntSignPosition(0),
                to = IntSignPosition(1),
            ),
            equivalentTo(IntSignPosition(1)),
        )
    }
}