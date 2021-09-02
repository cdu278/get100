package tickets.digits.next

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import cdu145.tickets.R
import tickets.ui.BottomButton

@Composable
fun NextNumberButton(
    viewModel: NextNumberButtonViewModel = viewModel<NextNumberButtonViewModelImpl>(),
) {
    BottomButton(
        onClick = { viewModel.loadNextNumber() },
    ) {
        Text(text = stringResource(R.string.nextNumberButton_text))
    }
}