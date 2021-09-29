package tickets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.get
import tickets.hint.AlmostThereDialog
import tickets.hint.AlmostThereDialogState
import tickets.hint.NoHintsAvailableDialogState
import tickets.hint.available.dialog.NoHintsAvailableDialog
import tickets.ui.state.DialogState
import tickets.ui.state.DialogState.Shown

@Composable
fun Dialogs(
    noHintsAvailableDialogStateFlow: StateFlow<DialogState> = get(NoHintsAvailableDialogState),
    almostThereDialogStateFlow: StateFlow<DialogState> = get(AlmostThereDialogState),
) {
    val noHintsAvailableDialogState by noHintsAvailableDialogStateFlow.collectAsState()
    if (noHintsAvailableDialogState == Shown) {
        NoHintsAvailableDialog()
        return
    }

    val almostThereDialogState by almostThereDialogStateFlow.collectAsState()
    if (almostThereDialogState == Shown) {
        AlmostThereDialog()
    }
}