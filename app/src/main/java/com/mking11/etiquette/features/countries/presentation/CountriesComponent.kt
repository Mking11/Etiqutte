package com.mking11.etiquette.features.countries.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

@Composable
fun CountriesComponent(countryPhoto: String,countryName:String,onCountryClick:(String) ->Unit) {

    Card(modifier = Modifier.fillMaxSize(0.8f)) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberImagePainter(countryPhoto),
                contentDescription = "flag ",
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(100.dp)
            )

            Spacer(modifier =Modifier.width(10.dp))

            Text(text = countryName,fontSize = 18.sp,textAlign = TextAlign.Start,overflow = TextOverflow.Ellipsis)
        }
    }

}