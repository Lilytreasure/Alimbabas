package com.example.composeapplication.features_products.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.composeapplication.R
import com.example.composeapplication.core.presentation.theme.ComposeApplicationTheme
import com.example.composeapplication.core.presentation.theme.DarkBlue
import com.example.composeapplication.core.presentation.theme.MainWhiteColor
import com.example.composeapplication.core.presentation.theme.YellowMain
import com.example.composeapplication.core.utils.LoadingAnimation
import com.example.composeapplication.core.utils.UiEvents
import com.example.composeapplication.features_products.domain.model.ProductList
import com.example.composeapplication.features_products.presentation.destinations.ProductsDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest


//This will outline  the items as  they appear in the home screen
//This container will populate items in a grid view state
// This will hold the items in  the home screen
//add a lazy column to populate  the list of items  in the products container



//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

//while setting the destination of the items  --run the compilatuons first to generate the files


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RootNavGraph(start = true)
@Destination
@Composable
 fun Homescreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel= hiltViewModel()

) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(0.dp)){
        Text(text = "Hello")

    }




Scaffold(
    topBar = {
        MyTopAppBar(viewModel)
    },


    ) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackbarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                else -> {}
            }
        }
    }


    //map the products in the  containers
    //create a  container to hold the image  and the  products descriptions


    val productsState = viewModel.productsState.value
    val categories = viewModel.categoriesState.value


    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Card(
                    elevation = 0.dp,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                               // .data(data = viewModel.bannerImageState.value)
                                .apply(block = fun ImageRequest.Builder.() {
                                    placeholder(R.drawable.ic_placeholder)
                                    crossfade(true)
                                }).build()
                        ),
                        contentScale = ContentScale.Crop,
                        contentDescription = "top cars view "
                    )
                }

            } // Header for some banner Image

            // Some spacer
            item(span = { GridItemSpan(2) }) {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item(span = { GridItemSpan(2) }) {
//The  categories will  be on a lazy horizontal row
                Categories(categories = categories, viewModel = viewModel)

            } // Header with a lazyRow for product categories

            // Some spacer
            item(span = { GridItemSpan(2) }) {
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Actual product items list
            items(productsState.products) { product ->
                ProductItem(
                    product = product,
                    navigator = navigator,
                    modifier = Modifier
                        .width(150.dp)
                )
            }
        }

        if (productsState.isLoading) {
            LoadingAnimation(
                modifier = Modifier.align(Alignment.Center),
                circleSize = 16.dp,
            )
        }

        if (productsState.error != null) Text(
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            text = productsState.error,
            color = Color.Red
        )
    }



}



}



//This will appear at the top of the application
@Composable
fun MyTopAppBar(
    viewModel: HomeViewModel
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(12.dp), /*horizontalAlignment = Alignment.CenterHorizontally*/
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = "")
                            .apply(block = fun ImageRequest.Builder.() {
                                crossfade(true)
                            }).build()
                    ),
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(35.dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Hi Dennis ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                tint = DarkBlue
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = viewModel.searchTerm.value,
                onValueChange = {
                    viewModel.setSearchTerm(it)
                },
                placeholder = {
                    Text(
                        text = "Search",
                        //color = primaryGray
                    )
                },

                modifier = Modifier
                    .fillMaxWidth(0.80f)
                    .background(MainWhiteColor, shape = RoundedCornerShape(8.dp))
                    .clickable {

                    },
                shape = RoundedCornerShape(size = 8.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                ),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    disabledTextColor = MainWhiteColor,
                    backgroundColor = MainWhiteColor,
                    focusedIndicatorColor = MainWhiteColor,
                    unfocusedIndicatorColor = MainWhiteColor,
                    disabledIndicatorColor = MainWhiteColor
                ),
                textStyle = TextStyle(color = Color.Black),
                maxLines = 1,
                singleLine = true,
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = DarkBlue
                    )
                }
            )

            IconButton(onClick = {
            }) {
                Icon(
                    modifier = Modifier
                        .size(55.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                size = 8.dp
                            )
                        )
                        .background(
                            MainWhiteColor
                        )
                        .padding(
                            start = 4.dp,
                            end = 4.dp,
                            top = 4.dp,
                            bottom = 4.dp
                        ),
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = null,
                    tint = DarkBlue
                )
            }
        }
    }
}
//a  Composable  function that holds the products
//This  will be rendered in  the  grid
//will set  the items  to 2 by default


@Composable
private fun ProductItem(
    product:ProductList,
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .clickable {
                navigator.navigate(ProductsDetailsScreenDestination(product))
            },
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = product.image)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.ic_placeholder)
                            crossfade(true)
                        }).build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Inside
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.category,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraLight
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Top,
            ) {
                Icon(
                    modifier = Modifier
                        .size(18.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    tint = YellowMain
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "${product.rating.rate} (${product.rating.count})",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$${product.price}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.End),
                shape = CircleShape,
                border = BorderStroke(0.dp, Color.Transparent),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White,
                    backgroundColor = YellowMain
                )
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Home,
                    contentDescription = null,
                    tint = MainWhiteColor
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }

    }
}

//Add the categories Composable

@Composable
fun Categories(categories: List<String>, viewModel: HomeViewModel) {
    LazyRow(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(categories) { category ->
            Text(
                text = category,
                style = MaterialTheme.typography.body1.merge(),
                color = Color.Black,
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            size = 8.dp,
                        ),
                    )
                    .clickable {
                        viewModel.setCategory(category)
                        viewModel.getProducts(viewModel.selectedCategory.value)
                    }
                    .background(
                        if (category == viewModel.selectedCategory.value) {
                            YellowMain
                        } else {
                            MainWhiteColor
                        }
                    )
                    .padding(
                        10.dp
                    )
            )
        }
    }
}

















@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeApplicationTheme {
        //add custom items to render  the home screen
//        ProductItem(product = ProductList(2,"barsoap",22.0,"sweater","warm","", Rating(2.0,5)),
//            )



      //Homescreen()
    }
}





