package com.krishworld.jetpack_compose_demo.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavHostController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.krishworld.jetpack_compose_demo.components.*
import com.krishworld.jetpack_compose_demo.ui.theme.Purple40
import kotlinx.coroutines.launch

@Composable
@UnstableApi
fun Dashboard(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        DashboardPage(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@UnstableApi
@Composable
fun DashboardPage(navController: NavHostController) {
    //For Drawer
    val drawerState: DrawerState =
        rememberDrawerState(initialValue = DrawerValue.Closed)

    //For fragments
    val selectedIndex = rememberSaveable { mutableStateOf(0) }
    val scaffoldState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()
    val showFab = remember { mutableStateOf(true) }
    val openDialogAlert = remember { mutableStateOf(false) }
    val openDialogCustom = remember { mutableStateOf(false) }

    //---------For floatingActionButton show/hide according to scroll-----------------
    val fabHeight = 160.dp //FabSize+Padding
    val fabHeightPx = with(LocalDensity.current) { fabHeight.roundToPx().toFloat() }
    val fabOffsetHeightPx = remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = fabOffsetHeightPx.value + delta
                fabOffsetHeightPx.value = newOffset.coerceIn(-fabHeightPx, 0f)
                if (newOffset > fabOffsetHeightPx.value) {
                    // Scrolled up
                    showFab.value = true
                } else if (newOffset < fabOffsetHeightPx.value) {
                    // Scrolled down
                    showFab.value = false
                }
                return Offset.Zero
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerView(drawerState = drawerState, scope = scope)
        }) {

        Scaffold(
            modifier = Modifier.nestedScroll(nestedScrollConnection),
            snackbarHost = { SnackbarHost(scaffoldState) },
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Home")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            //----------------------to show Drawer---------------
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            },
            content = {
                Box(
                    modifier = Modifier
                        .padding(
                            top = it.calculateTopPadding(),
                            bottom = it.calculateBottomPadding()
                        )
                ) {
                    when (selectedIndex.value) {
                        0 -> {
                            HomeScreen(navController = navController)
                        }

                        1 -> {
                            AnimationFragment(navController)
                        }

                        2 -> {
                            ProfileFragment(navController)
                        }

                        3 -> {
                            MoreFragment(navController)
                        }

                        else -> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Under Development Tab ${selectedIndex.value + 1}",
                                    modifier = Modifier.padding(20.dp)
                                )
                            }
                        }
                    }
                    AlertDialogView(openDialogAlert)
                    CustomDialog(openDialogCustom)
                }
            },
            bottomBar = {
                BottomAppBarView(selectedIndex)
            },
            floatingActionButton = {
                if (showFab.value)
                    FlowRow(
                        modifier = Modifier
                            .padding(8.dp),
                        mainAxisAlignment = FlowMainAxisAlignment.Center,
                    ) {
                        ExtendedFloatingActionButton(
                            modifier = Modifier.padding(start = 16.dp),
                            content = { Text("Alert Dialog") },
                            containerColor = Purple40,
                            onClick = {
                                //----------------------to show Dialog(Alert) ---------------
                                openDialogAlert.value = true
                            }
                        )
                        ExtendedFloatingActionButton(
                            modifier = Modifier.padding(start = 16.dp),
                            content = { Text("Dialog") },
                            containerColor = Purple40,
                            onClick = {
                                //----------------------to show Dialog(Custom) ---------------
                                openDialogCustom.value = true
                            }
                        )
                    }
            }
        )
    }
}






