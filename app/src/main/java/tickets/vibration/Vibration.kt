package tickets.vibration

import android.os.Build
import android.os.VibrationEffect
import android.os.VibrationEffect.createOneShot
import android.os.Vibrator

private const val OneShotDuration = 500L

class Vibration(
    private val vibrator: Vibrator,
) {

    fun oneShot() {
        with (vibrator) {
            if (!hasVibrator()) return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrate(createOneShot(OneShotDuration, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrate(OneShotDuration)
            }
        }
    }
}