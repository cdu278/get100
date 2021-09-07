package tickets

import android.app.Application
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tickets.coroutine.scope.CoroutineScopeModule
import tickets.digits.TicketDigitsModule
import tickets.number.EnsureTicketNumberCreated
import tickets.number.TicketNumberModule
import tickets.solution.gap.SolutionGapsModule
import tickets.solution.result.SolutionResultModule
import tickets.solution.signs.SolutionSignsModule

class TicketsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TicketsApplication)
            modules(CoroutineScopeModule + TicketNumberModule + TicketDigitsModule
                    + SolutionGapsModule + SolutionSignsModule + SolutionResultModule)
        }

        getKoin().get<EnsureTicketNumberCreated>().invoke()
    }
}