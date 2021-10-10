package tickets.vibration

import android.content.Context
import androidx.core.content.getSystemService
import org.koin.dsl.module

val VibrationModule = module {
    factory {
        Vibration(
            vibrator = get<Context>().getSystemService()!!,
        )
    }
}