package tickets.coroutine.scope

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import org.koin.dsl.onClose

val ApplicationCoroutineScope = StringQualifier("ApplicationCoroutineScope")

val CoroutineScopeModule = module {
    single(ApplicationCoroutineScope) {
        CoroutineScope(SupervisorJob())
    } onClose { it?.cancel() }
}