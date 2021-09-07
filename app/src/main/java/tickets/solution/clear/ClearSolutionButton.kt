package tickets.solution.clear

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cdu145.tickets.R
import org.koin.androidx.compose.getViewModel
import tickets.ui.BottomButton

@Composable
fun ClearSolutionButton(
    viewModel: ClearSolutionButtonViewModel = getViewModel<ClearSolutionButtonViewModelImpl>(),
) {
    BottomButton(
        onClick = { viewModel.clearSolution() },
    ) {
        Text(stringResource(R.string.clearSolutionButton_text))
    }
}