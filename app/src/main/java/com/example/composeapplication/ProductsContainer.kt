package com.example.composeapplication


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composeapplication.data.ProductList
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.composeapplication.data.Rating
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize


@Composable
fun  ProductsContainer(productList: ProductList){


    Column(modifier = Modifier
        .wrapContentHeight()
        .padding(top = 5.dp)
        .fillMaxWidth()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(modifier = Modifier
            .padding(top = 5.dp, start = 5.dp)
            .wrapContentHeight()
            ,
                text = productList.title,
            fontWeight = FontWeight.Bold
        )
        
        Row() {
            //image holder
      AsyncImage(
          model = productList.image,
          contentDescription = "",
      modifier = Modifier
          .height(200.dp)
          .width(200.dp)
          .wrapContentHeight())


            Text(modifier = Modifier.padding(5.dp),

                text = "$ "+ productList.price.toString())
            
        }

        //add The rating container
        Row(modifier = Modifier
            .wrapContentWidth()
            .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

           // RatingBar(rating = 3.7f, spaceBetween = 3.dp)\
            //Add the custom rating bar
            Text(modifier = Modifier.padding(end = 4.dp),
                text = "Rating")

            var rating: Float by remember { mutableStateOf(productList.rating.rate.toFloat()) }

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
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
                Spacer(modifier = Modifier.width(8.dp))
                Text(

                    //put the count of the ratings in brackets
                    text = "(${productList.rating.count})",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )

            }



        }


        Text(modifier = Modifier
            .padding(top = 5.dp, start = 5.dp)
            .wrapContentHeight(),
            text = productList.description)

    }


    //end of the products  list container
}



@Preview(showBackground = true)
@Composable
fun ProductsPreview() {
    ComposeApplicationTheme {

        var productList=ArrayList<ProductList>()



       // ProductsContainer(productList)
        //the rate of the rating is represented by the stars while
        // The rate count is in brackets


        val products=ProductList(2,"Sugar",22.0,"good sweater","", Rating(rate = 3.0,2))
        ProductsContainer(productList = products)
      //  RatingBar(rating = 3.7f, spaceBetween = 3.dp)


    }
}



