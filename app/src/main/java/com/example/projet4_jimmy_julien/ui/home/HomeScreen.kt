package com.example.projet4_jimmy_julien.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projet4_jimmy_julien.ui.AppViewModelProvider
import com.example.projet4_jimmy_julien.R
import com.example.projet4_jimmy_julien.TodoTopAppBar
import com.example.projet4_jimmy_julien.data.Todo
import com.example.projet4_jimmy_julien.ui.navigation.NavDestination


object HomeDestination : NavDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToTodoEntry: () -> Unit,
    navigateToTodoUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val homeUiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TodoTopAppBar(
                title = stringResource(R.string.app_name),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToTodoEntry,
                shape = RoundedCornerShape(topEnd = 3.dp, bottomEnd = 3.dp, topStart = 5.dp, bottomStart = 5.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.todo_add_title)
                )
            }
        },
    ) { innerPadding ->
        HomeBody(
            todoList = homeUiState.todoList,
            onTodoClick = navigateToTodoUpdate,
            modifier = modifier.fillMaxSize(),
            contentPadding = innerPadding,
        )
    }
}

@Composable
private fun HomeBody(
    todoList: List<Todo>,
    onTodoClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (todoList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_todos_inData),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding),
            )
        } else {
            TodoList(
                todoList = todoList,
                onTodoClick = { onTodoClick(it.id) },
                contentPadding = contentPadding,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
private fun TodoList(
    todoList: List<Todo>,
    onTodoClick: (Todo) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = todoList, key = { it.id }) { item ->
            TodoItem(todo = item,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onTodoClick(item) })
        }
    }
}

@Composable
private fun TodoItem(
    todo: Todo, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = todo.done,
                    onCheckedChange = {/*todo*/ }
                )

                Text(
                    text = todo.dateCreation,
                    style = MaterialTheme.typography.titleLarge,
                )
                /*UnBouton(expanded, { onExpanded(todo) })*/

            }
            Spacer(Modifier.height(5.dp))
            Row(

            ) {
                Text(
                    text = todo.nom,
                    style = MaterialTheme.typography.titleLarge,
                )
                /* DANS EXPANDED Text(
                    text = todo.note,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = item.formatedPrice(),
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = stringResource(R.string.in_stock, item.quantity),
                    style = MaterialTheme.typography.titleMedium
                )*/
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Delete,
                        contentDescription = "DeleteIcon")
                }
            }
        }
    }
}

/*@Composable
fun UnBouton(
    ouvert: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (ouvert) Icons.Filled.ExpandLess else Icons
                .Filled.ExpandMore,
            contentDescription = "Ouvrir",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}*/
