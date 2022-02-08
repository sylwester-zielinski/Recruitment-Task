package no.nordicsemi.recruitment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import no.nordicsemi.recruitment.home.view.HomeView
import no.nordicsemi.recruitment.web.NordicWebPage

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NordicTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = NavigationConst.HOME
                ) {
                    composable(NavigationConst.HOME) { HomeView(navController) }
                    composable(NavigationConst.NORDIC_PAGE) { NordicWebPage(navController) }
                }
            }
        }
    }
}
