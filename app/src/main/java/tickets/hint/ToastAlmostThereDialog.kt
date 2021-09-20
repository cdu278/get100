package tickets.hint

import android.content.Context
import android.widget.Toast
import cdu145.tickets.R

class ToastAlmostThereDialog(
    private val context: Context,
) : AlmostThereDialog {

    override fun show() {
        Toast.makeText(context, R.string.almostThereDialog_toastText, Toast.LENGTH_SHORT).show()
    }
}