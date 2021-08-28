package tickets.solution.signs.position

import com.natpryce.hamkrest.assertion.assertThat
import org.junit.jupiter.api.Test
import tickets.solution.signs.ArithmeticSign.*
import tickets.solution.signs.FragmentOfSolutionSigns

internal class LowestPrioritySignPositionFromTests {

    @Test
    fun `Should select from the given positions only`() {
        assertThat(
            LowestPrioritySignPositionFrom(
                RangeSignPositions(1, 1),
                FragmentOfSolutionSigns(PLUS, TIMES),
            ),
            equivalentTo(SignPosition(1)),
        )
    }

    @Test
    fun `From signs of equal priority should select the last one`() {
        assertThat(
            LowestPrioritySignPositionFrom(
                RangeSignPositions(0, 1),
                FragmentOfSolutionSigns(NONE, NONE),
            ),
            equivalentTo(SignPosition(1)),
        )
    }

    @Test
    fun `From signs of non-equal priority should select one having lower priority`() {
        assertThat(
            LowestPrioritySignPositionFrom(
                RangeSignPositions(0, 1),
                FragmentOfSolutionSigns(NONE, TIMES),
            ),
            equivalentTo(SignPosition(1)),
        )
    }
}