package tickets.solution.clear

import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cdu145.tickets.R
import org.koin.androidx.compose.getViewModel
import tickets.ui.Margin

@Composable
fun ClearSolutionButton(
    viewModel: ClearSolutionButtonViewModel = getViewModel(),
) {
    Button(
        onClick = { viewModel.clearSolution() },
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_clear),
            contentDescription = null,
        )
        Margin(4.dp)
        Text(stringResource(R.string.clearSolutionButton_text))
    }
}