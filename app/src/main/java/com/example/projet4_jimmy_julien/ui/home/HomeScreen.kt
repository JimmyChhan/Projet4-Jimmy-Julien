package com.example.projet4_jimmy_julien.ui.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
                shape = RoundedCornerShape(8.dp)
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
    contentPadding: PaddingValues = PaddingValues(4.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (todoList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_todos_inData),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding)
            )
        } else {
            TodoList(
                todoList = todoList,
                onTodoClick = { onTodoClick(it.id) },
                contentPadding = contentPadding,
                modifier = Modifier.padding(14.dp)
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
            TodoItem(
                todo = item,
                modifier = Modifier
                    .padding(vertical = 18.dp, horizontal = 10.dp)
                    .clickable { onTodoClick(item) })
        }
    }
}

@Composable
private fun TodoItem(
    todo: Todo,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.background
    )

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp, vertical = 8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            /* METTRE LA DATE DE CREATION ICI SÉPARÉ */
            /*
                Text(
                    text = todo.dateCreation,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )*/
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = todo.done,
                    onCheckedChange = { }
                )
                Text(
                    text = todo.nom,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier.weight(1f)
                )
                UnBouton(
                    ouvert = expanded,
                    onClick = {
                        expanded = !expanded
                    }
                )
                Spacer(Modifier.height(8.dp))
            }

            if (expanded) {
                Spacer(Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = todo.note,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = modifier
                            .padding(start = 4.dp)
                            .weight(1f)
                    )
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = "UpdateIcon"
                        )
                    }
                }
                Spacer(Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.todo_priority_label),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = modifier.padding(start = 4.dp)
                    )
                    Text(
                        text = todo.priority.toString(),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = modifier.weight(1f)
                    )
                    IconButton(
                        onClick = { /*todo delete*/ },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "DeleteIcon"
                        )
                    }
                }
            }
        }
    }
}

@Composable
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
            tint = MaterialTheme.colorScheme.tertiary
        )
    }
}


//RESTE À FAIRE:
/*
ACTION BOUTON DONE
ACTION BOUTTON EDIT
ACTION BOUTTON DELETE
FAIRE FONCTION ET AFFICHER LA DATE DE CRÉATION LOCAL TIME
GROSSEUR DU TITRE TASK MANAGER
+++ ADD MORE ON THE GO!

-OPTIONNEL-
***
LE UN SEUL TASK PEUT ETRE OUVERT
MODIFIER TAILLE DES TEXTES SI POSSIBLE
***
 */