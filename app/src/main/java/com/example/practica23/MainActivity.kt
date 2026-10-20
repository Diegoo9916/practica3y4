
package com.example.practica23

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practica23.ui.theme.Practica23Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            Practica23Theme {
                ViewContainer()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, locale = "es-rMX")
@Composable
fun ViewContainer() {

    // Estado del Snackbar
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    // Permite mostrar el Snackbar con una corrutina
    val coroutineScope = rememberCoroutineScope()

    // Mensaje obtenido desde strings.xml
    val snackbarMessage = stringResource(
        R.string.home_fab_text
    )

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.home_screen)
                    )
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    titleContentColor = Color.White
                ),

                navigationIcon = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    "Presionaste el botón de regresar"
                                )
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(
                                R.string.back_btn_desc
                            ),
                            tint = Color.White
                        )
                    }
                }
            )
        },

        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.bottom_app_bar),
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = snackbarMessage
                        )
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(
                        R.string.home_fab_text
                    )
                )
            }
        }
    ) { innerPadding ->

        ContentView(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ContentView(modifier: Modifier = Modifier) {


    var counter by rememberSaveable {
        mutableIntStateOf(0)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {

        item {

            Image(
                painter = painterResource(
                    id = R.drawable.androidlogo
                ),
                contentDescription = stringResource(
                    R.string.image_description
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Agregar a favoritos",
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            counter++
                        }
                )

                Text(
                    text = counter.toString(),
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Text(
                text = stringResource(R.string.materia),
                fontSize = 24.sp,
                color = Color.Blue,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(R.string.nombre),
                fontSize = 25.sp,
                color = Color.Magenta,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(R.string.apellido),
                fontSize = 24.sp,
                color = Color.Blue,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        item {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                items(
                    listOf(
                        R.drawable.img1,
                        R.drawable.img2,
                        R.drawable.img3,
                        R.drawable.img4
                    )
                ) { imageId ->

                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = "Imagen de aplicaciones móviles",
                        modifier = Modifier
                            .size(
                                width = 200.dp,
                                height = 100.dp
                            )
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}
