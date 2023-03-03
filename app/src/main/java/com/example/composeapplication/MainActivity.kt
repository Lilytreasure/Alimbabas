package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapplication.data.ProductList
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import com.example.composeapplication.viewmodel.ProductsViewModel

class MainActivity : ComponentActivity() {


    val productsViewModel by viewModels <ProductsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    
                    //  This is the entry that will be populated first when the application  is launched
                    //MainEntry()
                    //add  the main entry   to populate  in the main view
                    MainEntry(productList = productsViewModel.productsListResponse)
                    productsViewModel.getProductList()



                }
            }
        }
    }
}
//The lazy column to show the data


@Composable
fun MainEntry(productList: List<ProductList>){
    //main entry page to the application
    //add the ui and the item viewHolders
    // adding the  click events  to items in the holder

    //val productsViewModel by viewModels <ProductsViewModel>()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)) {
        TopAppBar(modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.DarkGray) {

            Text(modifier = Modifier.padding(start = 5.dp),
                text = "Soko",
                color = Color.White,
                fontSize = 15.sp)

        }

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .weight(1f),
        ){

            //add the items from the api

            //map the list of the items as populated  by  the api
            //add the items container as an item   in the lazy column

            itemsIndexed(items = productList){index, item ->
                //add  the  products container as an  item
                ProductsContainer(productList = item)

            }



        }


    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeApplicationTheme {
        //Preview the application

        //MainEntry()
        val products=ProductList(2,"Sugar",22.0,"good sweater","")
    ProductsContainer(productList = products)

    }
}