package cdu145.tickets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cdu145.tickets.solution.TicketSolutionCard
import cdu145.tickets.ticket.AnimatedTicketView
import tickets.ui.theme.ComposeTicketsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent { Content() }
    }
}

@Composable
private fun Content() {
    ComposeTicketsTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(MaterialTheme.colors.background),
        ) {
            TicketsTopBar()

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
            ) {
                AnimatedTicketView(elevation = 2.dp)
                TicketSolutionCard(elevation = 4.dp)
            }
        }

        DialogsOverlay()
    }
}