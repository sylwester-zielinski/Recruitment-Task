package no.nordicsemi.recruitment.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import no.nordicsemi.recruitment.NavigationConst
import no.nordicsemi.recruitment.R
import no.nordicsemi.recruitment.home.viewmodel.HomeViewModel

@Composable
fun HomeView(navController: NavController) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val state = viewModel.tasks.collectAsState().value

    Column {
        TopAppBar(
            title = { Text(stringResource(id = R.string.app_name)) },
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.nordic_web_page_action),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(8.dp)
                        .clickable { navController.navigate(NavigationConst.NORDIC_PAGE) }
                )
            }
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Column {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    OutlinedTextField(
                        value = state.displayedText,
                        onValueChange = { viewModel.onTextChanged(it) },
                        label = { Text(stringResource(id = R.string.task_label)) },
                        isError = state.error != null,
                        modifier = Modifier.weight(1f),
                        trailingIcon = {
                            Button(
                                onClick = { viewModel.addNewTask() },
                                modifier = Modifier.padding(end = 16.dp)
                            ) {
                                Text(stringResource(id = R.string.add))
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))

                getErrorString(state.error)?.let {
                    Text(
                        text = it,
                        fontSize = 10.sp,
                        color = MaterialTheme.colors.error
                    )
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            ItemsList(items = state.tasks)
        }
    }
}

@Composable
private fun getErrorString(error: FieldError?): String? {
    return when (error) {
        FieldError.EMPTY -> stringResource(id = R.string.error_empty)
        FieldError.ALREADY_EXIST -> stringResource(id = R.string.error_already_exist)
        null -> null
    }
}

@Composable
private fun ItemsList(items: List<String>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items.size) { i ->
            TaskItem(item = items[i])
        }
    }
}

@Composable
private fun TaskItem(item: String) {
    //TODO this value doesn't preserve scrolling
    val isPressed = remember { mutableStateOf(true) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                //TODO navigate to next screen
            }
            .padding(horizontal = 8.dp),
    ) {
        Text(
            text = item,
            modifier = Modifier.weight(1f)
        )

        val icon = if (isPressed.value) {
            Icons.Default.PlayArrow
        } else {
            Icons.Default.Pause
        }
        Icon(
            icon,
            contentDescription = stringResource(id = R.string.task_play),
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    isPressed.value = !isPressed.value
                }
                .padding(8.dp)
        )
    }
}
