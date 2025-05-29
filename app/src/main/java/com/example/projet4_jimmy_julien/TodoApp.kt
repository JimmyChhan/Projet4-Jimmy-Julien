package com.example.projet4_jimmy_julien

import android.widget.Space
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projet4_jimmy_julien.ui.navigation.TodoNavHost


@Composable
fun TodoApp(navController: NavHostController = rememberNavController()) {
    TodoNavHost(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {},
    openFilter: () -> Unit = {}
) {
    CenterAlignedTopAppBar({
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 30.dp, bottom = 20.dp)){
            Icon(
                imageVector = Icons.Default.AddTask,
                modifier = Modifier.size(40.dp),
                contentDescription = null
            )
            Spacer(Modifier.width(5.dp))
            Text(text = title, style = MaterialTheme.typography.displaySmall)
        }
    },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                        contentDescription = ""
                    )
                }
            }else{
                IconButton(onClick = openFilter) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_menu_24),
                        contentDescription = ""
                    )
                }
            }
        })
}
