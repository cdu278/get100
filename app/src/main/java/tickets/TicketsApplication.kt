package tickets

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tickets.di.AppModule

class TicketsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TicketsApplication)
            modules(AppModule)
        }
    }
}