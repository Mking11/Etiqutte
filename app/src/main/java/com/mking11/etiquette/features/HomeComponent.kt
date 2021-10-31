package com.mking11.etiquette.features

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mking11.etiquette.R
import com.mking11.etiquette.features.navigation.Screens
import com.mking11.etiquette.ui.theme.EtiquetteTheme

@Composable
fun HomeComponent(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.image),
            contentDescription = "home image",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),contentScale = ContentScale.Fit
        )


        Text(
            "How about some Etiquette ?",
            fontSize = 40.sp,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .padding(start = 30.dp, end = 30.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6
        )


        Button(
            onClick = {
                navController.navigate(Screens.COUNTRIES)
            },
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 20.dp)
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.Gray.copy(alpha = 0.2f), shape = RoundedCornerShape(25))
        ) {
            Text("Go!", fontSize = 20.sp)

        }


    }
}


