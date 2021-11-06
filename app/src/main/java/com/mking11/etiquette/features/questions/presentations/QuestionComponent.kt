package com.mking11.etiquette.features.questions.presentations

import android.graphics.Bitmap
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.mking11.etiquette.features.questions.domain.models.dbo.OptionsDbo

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun QuestionComponent(
    questionText: String,
    validResponseId: String,
    questionPhoto: Bitmap?,
    questionId: String,
    viewModel: OptionsViewModel = hiltViewModel(),
    revealAnswers: Boolean,
) {


    val options = viewModel.getOptions(questionId)?.collectAsState(listOf())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Vertical,
                enabled = true
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {


        val checked = remember {
            mutableStateOf<String>("")
        }

        Text(
            text = questionText,
            textAlign = TextAlign.Center,
            fontSize = 26.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )


        Spacer(modifier = Modifier.height(20.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(200.dp), elevation = 4.dp
        ) {


            Image(
                painter = rememberImagePainter(questionPhoto, viewModel.imageLoader),
                contentDescription = "question image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )

        }

        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        ) {


            val _options: List<OptionsDbo>? = options?.value
            if (_options != null) {
                items(_options) {
                    OptionsComponent(
                        checked = checked.value == it.id,
                        optionId = it.id,
                        isValidAnswer = it.id == validResponseId,
                        revealResult = revealAnswers,
                        value = it.title
                    ) {
                        checked.value = it
                    }
                }
            }

        }


    }
}


//@ExperimentalAnimationApi
//@Preview
//@Composable
//fun previewQuesitons() {
//    QuestionComponent(
//        "How would you behave in the following situation ?",
//
//        "options 1",
//        false,
//        options = listOf<OptionsDbo>(
//            OptionsDbo("option 1", "questionID", "option 1"),
//            OptionsDbo("option 2", "questionID", "option 2"),
//            OptionsDbo("option 3", "questionID", "option 3")
//        )
//    )
//}

//
//@Preview
//@Composable
//fun previewQuestionParent() {
//    QuestionParent()
//}