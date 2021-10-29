package com.mking11.etiquette.features.countries.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mking11.etiquette.features.countries.domain.models.CountriesDbo
import com.mking11.etiquette.features.navigation.Screens

@Composable
fun SelectCountriesComponent(
    navHostController: NavHostController,
    countriesViewModel: CountriesViewModel = hiltViewModel()
) {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {

        val (top_constriant, list_constraint) = createRefs()
        Text(
            "Select Country",
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.constrainAs(top_constriant) {
                start.linkTo(parent.start, 20.dp)
                end.linkTo(parent.end, 20.dp)
                top.linkTo(parent.top, 30.dp)
                width = Dimension.fillToConstraints
            })

        val countries: List<CountriesDbo>? = countriesViewModel.countries?.collectAsState(listOf())?.value

        LazyColumn(modifier = Modifier.fillMaxWidth().constrainAs(list_constraint) {
            start.linkTo(parent.start, 20.dp)
            end.linkTo(parent.end, 20.dp)
            top.linkTo(top_constriant.bottom, 20.dp)
            bottom.linkTo(parent.bottom, 20.dp)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        },horizontalAlignment = Alignment.CenterHorizontally) {
            if (countries != null) {
                items(countries) {
                    CountriesComponent(it.photo, it.countryName,it.countryId) { countryId ->
                        navHostController.navigate(Screens.CATEGORY + "/${countryId}")
                    }
                }
            }

        }

    }
}

