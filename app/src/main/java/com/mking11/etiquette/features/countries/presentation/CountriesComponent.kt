package com.mking11.etiquette.features.countries.presentation

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberImagePainter

@Composable
fun CountriesComponent(
    countryPhoto: Bitmap,
    countryName: String,
    countryId:String,
    imageLoader: ImageLoader,
    onCountryClick: (String) -> Unit
) {

    Card(modifier = Modifier.fillMaxWidth().padding(top = 10.dp).clickable {
        onCountryClick(countryId)
    }, elevation = 4.dp) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Card {
                Image(
                    painter = rememberImagePainter(countryPhoto,imageLoader = imageLoader),
                    contentDescription = "flag ",
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(100.dp), contentScale = ContentScale.Fit
                )
            }


            Spacer(modifier = Modifier.width(20.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = countryName,
                fontSize = 18.sp,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}