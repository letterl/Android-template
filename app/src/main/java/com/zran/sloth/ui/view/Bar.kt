package com.zran.sloth.ui.view

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zran.sloth.entity.BarEntity
import com.zran.sloth.entity.NoteContract
import com.zran.sloth.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    val viewModel:HomeViewModel = viewModel()
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    MediumTopAppBar (
        title = { Text("我的收藏") },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch{
                    viewModel.showToast("123")
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Localized description"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerBar(){
    val vm:HomeViewModel = viewModel()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    scope.coroutineContext.let {
        scope.launch {
            delay(1000*5)
            drawerState.open()
            vm.effect.collect {
                drawerState.open()
            }
        }
    }
    NavigationDrawer(
        modifier = Modifier.zIndex(1f),
        drawerState = drawerState,
        drawerContent = {
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                onClick = { scope.launch { drawerState.close() } },
                content = { Text("Close Drawer") }
            )
        },
        content = {
            Button(
                onClick = { scope.launch { drawerState.close() } },
                content = { Text("Close Drawer") }
            )
        }
    )

}


@Composable
fun BottomBar(){
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        BarEntity("收藏", { Icon(Icons.Filled.Home, contentDescription = null) }, 1),
        BarEntity("管理", { Icon(Icons.Filled.Favorite, contentDescription = null) }, 2),
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = item.icon,
                label = { Text(item.title) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}