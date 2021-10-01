package tickets.digits.next

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
fun NextNumberButton(
    viewModel: NextNumberButtonViewModel = getViewModel<NextNumberButtonViewModelImpl>(),
) {
    Button(
        onClick = { viewModel.loadNextNumber() },
    ) {
        Text(text = stringResource(R.string.nextNumberButton_text))
        Margin(4.dp)
        Icon(
            painter = painterResource(R.drawable.ic_next),
            contentDescription = null,
        )
    }
}