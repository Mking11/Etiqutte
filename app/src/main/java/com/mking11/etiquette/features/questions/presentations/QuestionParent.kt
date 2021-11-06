package com.mking11.etiquette.features.questions.presentations

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import kotlinx.coroutines.delay

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun QuestionParent(
    navController: NavHostController,
    categoryId: String,
    countryId: String,

    viewModel: QuestionsViewModel = hiltViewModel()
) {

    val (questionNumber, setQuestionNumber) = remember {
        mutableStateOf(0)
    }


    val questions = viewModel.getQuestions(categoryId, countryId)?.collectAsState(listOf())?.value
    val size: Int? = questions?.size?.minus(1)

    val showSolution = remember { mutableStateOf(false) }



    LaunchedEffect(showSolution.value) {
        delay(2000L)

        if (showSolution.value){
            if (questionNumber < size ?: 0) {
                setQuestionNumber(questionNumber + 1)
                showSolution.value = false
            } else {
                navController.popBackStack()
            }
        }

    }




    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (top_constraint, bottom_constraint) = createRefs()

        Column(
            modifier = Modifier.padding(bottom = 50.dp).constrainAs(top_constraint) {
                top.linkTo(parent.top, 20.dp)
                bottom.linkTo(parent.bottom, 50.dp)
                start.linkTo(parent.start, 0.dp)
                end.linkTo(parent.end, 0.dp)

                height = Dimension.wrapContent
                width = Dimension.fillToConstraints
            },
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(15.dp))
                IconButton(onClick = {
                    navController.popBackStack()
                }, modifier = Modifier.fillMaxWidth(0.2f)) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = "go back"
                    )
                }

                Spacer(modifier = Modifier.width(50.dp))

                Text(
                    text = "Question",
                    textAlign = TextAlign.Start,
                    fontSize = 30.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (questions?.isEmpty() == true) {

                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Sorry No questions yet, will be updated shortly")
                }
            } else {


                AnimatedContent(
                    targetState = questionNumber,
                    transitionSpec = {
                        // Compare the incoming number with the previous number.
                        if (targetState > initialState) {
                            // If the target number is larger, it slides up and fades in
                            // while the initial (smaller) number slides up and fades out.
                            slideInHorizontally() + fadeIn() with
                                    slideOutHorizontally() + fadeOut()
                        } else {
                            // If the target number is smaller, it slides down and fades in
                            // while the initial number slides down and fades out.
                            slideInHorizontally() + fadeIn() with
                                    slideOutHorizontally() + fadeOut()
                        }.using(
                            // Disable clipping since the faded slide-in/out should
                            // be displayed out of bounds.
                            SizeTransform(clip = false)
                        )
                    }
                ) { targetCount ->

                    val question = questions?.getOrNull(targetCount)
                    if (question != null) {


                        QuestionComponent(
                            questionText = question.title,
                            validResponseId = question.validAnswer,
                            revealAnswers = showSolution.value,
                            questionId = question.id, questionPhoto = question.photo
                        )
                    }
                }


            }


        }

        Row(
            modifier = Modifier.height(100.dp)
                .constrainAs(bottom_constraint) {
                    bottom.linkTo(parent.bottom, 10.dp)
                    start.linkTo(parent.start, 0.dp)
                    end.linkTo(parent.end, 0.dp)

                    width = Dimension.fillToConstraints
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (questions != null) {
                Spacer(modifier = Modifier.width(20.dp))

                IconButton(
                    onClick = {
                        if (questionNumber != 0) {
                            setQuestionNumber(questionNumber - 1)
                        }
                        showSolution.value = false
                    },
                    modifier = Modifier.height(50.dp).width(50.dp).background(
                        color = Color.DarkGray.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(4.dp)
                    ),
                    enabled = questions.isNotEmpty()
                ) {
                    Icon(Icons.Default.SkipPrevious, contentDescription = "Back ward")

                }
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(
                    onClick = {
                        showSolution.value = true


                    },
                    enabled = !showSolution.value,
                    modifier = Modifier.height(80.dp).width(80.dp).background(
                        color = Color.DarkGray.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(50)
                    )
                ) {
                    Icon(Icons.Default.Visibility, contentDescription = "Show answers")
                }
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(
                    onClick = {
                        if (size != null) {
                            if (questionNumber < size) {
                                setQuestionNumber(questionNumber + 1)
                                showSolution.value = false
                            } else {
                                navController.popBackStack()
                            }
                        }

                    },
                    modifier = Modifier.height(50.dp).width(50.dp).background(
                        color = Color.DarkGray.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(4.dp)
                    ),
                    enabled = questionNumber < questions.size
                ) {
                    Icon(Icons.Default.SkipNext, contentDescription = "Back ward")

                }
                Spacer(modifier = Modifier.width(20.dp))
            }
        }


    }
}