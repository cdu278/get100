package tickets.solution.clear

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import cdu145.tickets.R
import tickets.ui.BottomButton

@Composable
fun ClearSolutionButton(
    viewModel: ClearSolutionButtonViewModel = viewModel<ClearSolutionButtonViewModelImpl>(),
) {
    BottomButton(
        onClick = { viewModel.clearSolution() },
    ) {
        Text(stringResource(R.string.clearSolutionButton_text))
    }
}