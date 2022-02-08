package no.nordicsemi.recruitment.web

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import no.nordicsemi.recruitment.R

private const val NORDIC_WEB_PAGE = "https://www.nordicsemi.com/"

@Composable
fun NordicWebPage(navController: NavController) {

    Column {
        TopAppBar(
            title = { Text(stringResource(id = R.string.nordic_web_page)) },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.nordic_web_page_action),
                    modifier = Modifier.clickable { navController.navigateUp() }
                )
            }
        )

        WebView()
    }
}

@Composable
private fun WebView() {
    AndroidView(factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()

            loadUrl(NORDIC_WEB_PAGE)
        }
    })
}
