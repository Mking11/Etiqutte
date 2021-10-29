package com.mking11.etiquette.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun HomeComponent(navController:NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.image),
            contentDescription = "home image",
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.6f)
        )


        Text(
            "How about some etiquette ?",
            fontSize = 40.sp,
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.3f)
                .padding(start = 30.dp, end = 30.dp), textAlign = TextAlign.Center
        )


        Button(onClick = {
            navController.navigate(Screens.COUNTRIES)
        }, modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 20.dp).width(150.dp).height(60.dp)) {
            Text("Go!", fontSize = 20.sp)

        }


    }
}
