package com.mking11.etiquette.features.categories.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mking11.etiquette.features.navigation.Screens

@Composable
fun SelectCategoryComponent(
    navController: NavController,
    countryId: String,
    categoriesViewModel: CategoriesViewModel = hiltViewModel()
) {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (top_constriant, list_constraint) = createRefs()

        Row(
            modifier = Modifier.constrainAs(top_constriant) {
                linkTo(parent.start, parent.end)
                top.linkTo(parent.top, 30.dp)
                end.linkTo(parent.end, 20.dp)

                width = Dimension.fillToConstraints
            },
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(onClick = {
                navController.popBackStack()
            }, modifier = Modifier.fillMaxWidth(0.2f)) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = "go back"
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                "Select Category",
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                fontSize = 30.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }


        val categories = categoriesViewModel.categories?.collectAsState(listOf())?.value

        LazyColumn(modifier = Modifier.constrainAs(list_constraint) {
            start.linkTo(parent.start, 20.dp)
            end.linkTo(parent.end, 20.dp)
            top.linkTo(top_constriant.bottom, 20.dp)
            bottom.linkTo(parent.bottom, 20.dp)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }, verticalArrangement = Arrangement.Center) {
            if (categories != null) {

                items(categories) {
                    CategoriesComponent(
                        it.photo,
                        it.name,
                        it.categoryId,
                        categoriesViewModel.imageLoader
                    ) { categoryId ->

                        navController.navigate(Screens.QUESTIONS + "/${countryId}/${categoryId}")

                    }
                }

            } else {
                item {
                    CircularProgressIndicator()
                }

            }
        }

    }
}
//@Preview
//@Composable
//fun previewListCategpry() {
//    SelectCategoryComponent()
//}
