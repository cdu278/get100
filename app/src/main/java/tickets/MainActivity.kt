package tickets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import tickets.solution.TicketSolutionCard
import tickets.ui.TicketsTopBar
import tickets.ui.theme.ComposeTicketsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Content() }
    }
}

@Composable
private fun Content() {
    ComposeTicketsTheme {
        Scaffold(
            topBar = { TicketsTopBar() },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter,
            ) {
                TicketSolutionCard()
            }
        }

        Dialogs()
    }
}