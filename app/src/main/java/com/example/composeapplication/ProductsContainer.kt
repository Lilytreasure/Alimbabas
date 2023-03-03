package com.example.composeapplication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapplication.data.ProductList
import com.example.composeapplication.ui.theme.ComposeApplicationTheme


@Composable
fun  ProductsContainer(productList: ProductList){

    Column(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(modifier = Modifier
            .padding(top = 5.dp, start = 5.dp)
            .wrapContentHeight(),
                text = productList.title)
        
        Row() {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription ="image",
                modifier = Modifier
                    .border(border = BorderStroke(2.dp, color = Color.Green)
                    )
            )

            Text(modifier = Modifier.padding(5.dp),

                text = productList.price.toString())
            
        }


        Text(modifier = Modifier
            .padding(top = 5.dp, start = 5.dp)
            .wrapContentHeight(),
            text = productList.description)

    }


    //end of the products  list container
}


//add the preview  container


@Preview(showBackground = true)
@Composable
fun ProductsPreview() {
    ComposeApplicationTheme {

        var productList=ArrayList<ProductList>()



       // ProductsContainer(productList)

        val products=ProductList(2,"Sugar",22.0,"good sweater","")
        ProductsContainer(productList = products)




    }
}



