package tickets.vibration

import android.content.Context
import androidx.core.content.getSystemService
import org.koin.dsl.module

val VibrationModule = module {
    factory {
        OneShotVibration(
            vibrator = get<Context>().getSystemService()!!,
        )
    }
}