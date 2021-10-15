package tickets.digits.next

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
        iconGravity = BottomButton.IconGravity.Right,
        modifier = Modifier, // Workaround for the NPE bug
        icon = {
            Icon(
                painter = painterResource(R.drawable.ic_next),
                contentDescription = null,
            )
        },
        text = {
            Text(
                stringResource(R.string.nextNumberButton_text),
            )
        },
    )
}