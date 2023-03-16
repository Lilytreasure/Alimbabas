package com.example.composeapplication.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapplication.core.presentation.components.CustomScaffold
//import com.example.composeapplication.core.presentation.components.CustomScaffold
import com.example.composeapplication.core.presentation.theme.ComposeApplicationTheme
import com.example.composeapplication.features_products.presentation.NavGraphs
import com.example.composeapplication.features_products.presentation.destinations.HomescreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint




@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //get  data from the remote repository and cache in the local storage
    //create an application that resembles jumia
    //write   to the api by submitting the number of liked items@
    //main  activity --will run by default as the entry point to the  application
    //when using the Third party libray for the navigation
    //Run the build to generate the  files
    //Todo--Handle exceptions to avois crashes



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Gray
                ) {


                    val navController = rememberNavController()
                    val navHostEngine = rememberNavHostEngine()
                    val newBackStackEntry by navController.currentBackStackEntryAsState()
                    val route = newBackStackEntry?.destination?.route



                    CustomScaffold(
                        navController = navController,
                        showBottomBar = route in listOf(
                            HomescreenDestination.route
                        )
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            DestinationsNavHost(
                                navGraph= NavGraphs.root,
                                navController = navController,
                                engine = navHostEngine
                            )
                        }
                    }


                }
            }
        }
    }
}
//The lazy column to show the data



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeApplicationTheme {


    }
}