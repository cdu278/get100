package cdu145.tickets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cdu145.tickets.digits.DigitCards
import cdu145.tickets.digits.next.NextNumberButton
import cdu145.tickets.hint.HintButton
import cdu145.tickets.solution.clear.ClearSolutionButton
import cdu145.tickets.solution.gaps.SolutionGapButtons
import cdu145.tickets.solution.result.SolutionResultView
import cdu145.tickets.solution.signs.SignButtons
import cdu145.ui.composable.Margin
import tickets.ui.theme.ComposeTicketsTheme

private val CornerSize = 20.dp

@Composable
fun TicketSolutionCard(
    elevation: Dp,
) {
    Surface(
        elevation = elevation,
        shape = RoundedCornerShape(topStart = CornerSize, topEnd = CornerSize),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Margin(48.dp)
            Box {
                DigitCards(
                    cardsElevation = 2.dp,
                )
                SolutionGapButtons(
                    buttonsElevation = ButtonDefaults.elevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 10.dp,
                        disabledElevation = 2.dp,
                    ),
                )
            }
            SolutionResultView()
            Margin(32.dp)
            SignButtons(
                spaceBetween = 24.dp,
            )
            Margin(48.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ClearSolutionButton()
                HintButton()
                NextNumberButton()
            }
            Margin(12.dp)
        }
    }
}

@Composable
@Preview(showSystemUi = true)
private fun TicketSolutionCardPreview() {
    ComposeTicketsTheme {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.background(MaterialTheme.colors.background),
        ) {
            TicketSolutionCard(elevation = 2.dp)
        }
    }
}