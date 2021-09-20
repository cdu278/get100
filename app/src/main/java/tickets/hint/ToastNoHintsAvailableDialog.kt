package tickets.hint

import android.content.Context
import android.widget.Toast
import cdu145.tickets.R

class ToastNoHintsAvailableDialog(
    private val context: Context,
) : HintButtonViewModel.NoHintsAvailableDialog {

    override fun show() {
        Toast.makeText(context, R.string.noHintsAvailableDialog_toastText, Toast.LENGTH_SHORT)
            .show()
    }
}