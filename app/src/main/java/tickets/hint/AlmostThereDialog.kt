package tickets.hint

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cdu145.tickets.R
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.get
import tickets.ui.SimpleDialog
import tickets.ui.dialogs.DialogState
import tickets.ui.dialogs.DialogState.Hidden
import tickets.ui.dialogs.DialogState.Shown

@Composable
fun AlmostThereDialog(
    state: MutableStateFlow<DialogState> = get(AlmostThereDialogState),
) {
    SimpleDialog(
        onDismissRequest = { state.value = Hidden },
        titleText = stringResource(R.string.almostThereDialog_title),
        contentText = stringResource(R.string.almostThereDialog_message),
        buttons = {
            Button(onClick = { state.value = Hidden }) {
                Text(stringResource(R.string.almostThereDialog_close))
            }
        }
    )
}

class AlmostThereDialogImpl(
    private val state: MutableStateFlow<DialogState>,
) : AlmostThereDialog {

    override fun show() {
        state.value = Shown
    }
}