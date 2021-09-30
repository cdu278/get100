package tickets.hint.restoring

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import tickets.coroutine.scope.ApplicationCoroutineScope

class DeleteRestoringHintBroadcastReceiver : BroadcastReceiver(), KoinComponent {

    companion object {

        const val USED_AT_KEY = "tickets.hint.restoring.USED_AT"
    }

    private val scope: CoroutineScope
        get() = get(ApplicationCoroutineScope)

    private val dao: RestoringHintsDao
        get() = get()

    override fun onReceive(context: Context, intent: Intent) {
        intent
            .getLongExtra(USED_AT_KEY, -1)
            .takeIf { it != -1L }
            ?.let { usedAt ->
                scope.launch {
                    dao.delete(RestoringHint(usedAt))
                }
            }
            ?: error("`used_at` value not passed")
    }
}