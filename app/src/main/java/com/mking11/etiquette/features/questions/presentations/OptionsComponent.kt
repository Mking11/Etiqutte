package com.mking11.etiquette.features.questions.presentations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalAnimationApi
@Composable
fun OptionsComponent(
    checked: Boolean,
    optionId: String,
    isValidAnswer: Boolean,
    revealResult: Boolean,
    value: String,
    onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f).clickable {
                onClick(optionId)
            }
            .padding(top = 5.dp, end = 5.dp, start = 5.dp, bottom = 5.dp), elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Checkbox(
                    modifier = Modifier.fillMaxWidth(0.2f),
                    checked = checked,
                    onCheckedChange = {
                        onClick(optionId)
                    })

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = value,
                    modifier = Modifier.fillMaxWidth(0.7f),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis
                )

                AnimatedVisibility(visible = revealResult) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (isValidAnswer) Icons.Default.Done else Icons.Default.Clear,
                            contentDescription = "Result",
                            tint = if (isValidAnswer) Color.Green else Color.Red
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }

            }
        }


    }
}

//@ExperimentalAnimationApi
//@Preview
//@Composable
//fun previewOptions() {
//    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//        OptionsComponent(checked = true, value = "The woman should eat first ",isValidAnswer = true,revealResult = true) {
//
//        }
//    }
//
//}
