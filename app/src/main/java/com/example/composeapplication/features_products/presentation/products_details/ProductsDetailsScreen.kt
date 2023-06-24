package com.example.composeapplication.features_products.presentation.products_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapplication.R
import com.example.composeapplication.core.presentation.theme.ComposeApplicationTheme
import com.example.composeapplication.features_products.domain.model.ProductList
import com.example.composeapplication.features_products.domain.model.Rating
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun ProductsDetailsScreen(
    navigator: DestinationsNavigator,
    productList: ProductList

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        ProductsTopBar()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "product image",
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                )

            }
            // Name of  the product
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .align(androidx.compose.ui.Alignment.CenterHorizontally),
                text = "Macbook Pro M2",
                textAlign = TextAlign.Center
            )
            //The rating bar
            val rating: Float by remember { mutableStateOf(productList.rating.rate.toFloat()) }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                RatingBar(
                    value = rating,
                    //the custom config file enables the user to
                    //customize the Rating bar
                    config = RatingBarConfig()
                        .activeColor(Color.Yellow)
                        .inactiveColor(Color.Gray)
                        .stepSize(StepSize.HALF)
                        .numStars(5)
                        .isIndicator(true)
                        .size(16.dp)
                        .padding(3.dp)
                        .style(RatingBarStyle.HighLighted),
                    onValueChange = {},
                    onRatingChanged = {}
                )
            }
        }
    }
}

//create a custom top  app bar
@Composable
fun ProductsTopBar() {

    Row(modifier = Modifier.fillMaxWidth()) {

        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White

        ) {
            Text(
                text = "Description",
                color = Color.Black
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    ComposeApplicationTheme {

        var product = ProductList(
            3, "macBook air", 100.00,
            "Macbook air mz 16gb Ram", "laptops", "", Rating(2.0, 34)
        )

    }
}

