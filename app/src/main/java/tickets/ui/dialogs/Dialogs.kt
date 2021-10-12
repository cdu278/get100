package tickets.ui.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.get
import org.koin.core.component.createScope
import org.koin.core.component.getScopeOrNull
import tickets.actual.Actual
import tickets.guide.*
import tickets.hint.AlmostThereDialog
import tickets.hint.AlmostThereDialogState
import tickets.hint.NoHintsAvailableDialogState
import tickets.hint.available.dialog.NoHintsAvailableDialog
import tickets.ui.dialogs.DialogState.Shown

@Composable
fun Dialogs(
    noHintsAvailableDialogStateFlow: StateFlow<DialogState> = get(NoHintsAvailableDialogState),
    almostThereDialogStateFlow: StateFlow<DialogState> = get(AlmostThereDialogState),
    guideCompletedFlag: Actual<Boolean> = get(GuideCompletedFlag),
    guideDialogStateFlow: MutableStateFlow<DialogState> = get(GuideDialogStateFlow),
) {
    val noHintsAvailableDialogState by noHintsAvailableDialogStateFlow.collectAsState()
    if (noHintsAvailableDialogState == Shown) {
        NoHintsAvailableDialog()
    }

    val almostThereDialogState by almostThereDialogStateFlow.collectAsState()
    if (almostThereDialogState == Shown) {
        AlmostThereDialog()
    }

    LaunchedEffect(null) {
        if (!guideCompletedFlag.value()) {
            guideDialogStateFlow.value = Shown
        }
    }
    val guideDialogState by guideDialogStateFlow.collectAsState()
    guideDialogState.let {
        if (it == Shown) {
            Guide.createScope()
            GuideDialog()
        } else {
            Guide.getScopeOrNull()?.close()
        }
    }
}