package tickets.hint.restoring

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.getSystemService

interface RestoringHintDeletion {

    fun schedule(hint: RestoringHint)
}

class AlarmManagerRestoringHintDeletion(
    private val context: Context,
) : RestoringHintDeletion {

    override fun schedule(hint: RestoringHint) {
        AlarmManagerCompat.setExact(
            /*alarmManager =*/context.getSystemService()!!,
            /*type =*/AlarmManager.RTC,
            /*triggerAtMillis =*/hint.usedAt + HintRestorationTime,
            /*operation =*/PendingIntent.getBroadcast(
                context,
                hint.usedAt.toInt(),
                Intent(context, DeleteRestoringHintBroadcastReceiver::class.java).apply {
                    putExtra(DeleteRestoringHintBroadcastReceiver.USED_AT_KEY, hint.usedAt)
                },
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    FLAG_IMMUTABLE or FLAG_UPDATE_CURRENT
                } else {
                    FLAG_UPDATE_CURRENT
                },
            )
        )

    }
}