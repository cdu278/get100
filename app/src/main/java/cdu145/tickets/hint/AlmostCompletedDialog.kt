package cdu145.tickets.hint

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cdu145.tickets.R
import cdu145.ui.composable.SimpleDialog
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.get
import cdu145.ui.state.DialogState
import cdu145.ui.state.DialogState.Hidden
import cdu145.ui.state.DialogState.Shown

@Composable
fun AlmostCompletedDialog(
    state: MutableStateFlow<DialogState> = get(AlmostCompletedDialogState),
) {
    SimpleDialog(
        onDismissRequest = { state.value = Hidden },
        titleText = stringResource(R.string.almostCompletedDialog_title),
        contentText = stringResource(R.string.almostCompletedDialog_message),
        buttons = {
            Button(onClick = { state.value = Hidden }) {
                Text(stringResource(R.string.almostCompletedDialog_close))
            }
        }
    )
}

class AlmostCompletedDialogImpl(
    private val state: MutableStateFlow<DialogState>,
) : AlmostCompletedDialog {

    override fun show() {
        state.value = Shown
    }
}