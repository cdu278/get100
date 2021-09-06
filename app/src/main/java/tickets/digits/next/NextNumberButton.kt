package tickets.digits.next

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cdu145.tickets.R
import org.koin.androidx.compose.getViewModel
import tickets.ui.BottomButton

@Composable
fun NextNumberButton(
    viewModel: NextNumberButtonViewModel = getViewModel<NextNumberButtonViewModelImpl>(),
) {
    BottomButton(
        onClick = { viewModel.loadNextNumber() },
    ) {
        Text(text = stringResource(R.string.nextNumberButton_text))
    }
}