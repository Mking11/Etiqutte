package com.mking11.etiquette

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.app.ActivityCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mking11.etiquette.features.HomeComponent
import com.mking11.etiquette.features.categories.presentation.SelectCategoryComponent
import com.mking11.etiquette.features.countries.presentation.SelectCountriesComponent
import com.mking11.etiquette.features.navigation.Arguments
import com.mking11.etiquette.features.navigation.Screens
import com.mking11.etiquette.features.questions.presentations.QuestionParent
import com.mking11.etiquette.ui.theme.EtiquetteTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestCodeStorageReadPermission = 1003

    private var hasPremissions = false

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    private val permissions = arrayOf(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.INTERNET,
    )

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        checkPermissions(permissions)
        setContent {

            EtiquetteTheme {

                val navController = rememberNavController()


                NavHost(navController = navController, startDestination = Screens.HOME) {
                    composable(Screens.HOME) {
                        HomeComponent(navController)
                    }

                    composable(Screens.COUNTRIES) {
                        SelectCountriesComponent(navController)
                    }

                    composable(Screens.CATEGORY + "/{${Arguments.COUNTRY_ID}}", arguments = listOf(
                        navArgument(Arguments.COUNTRY_ID) {
                            type = NavType.StringType
                        }
                    )){
                        val countryId = it.arguments?.getString(Arguments.COUNTRY_ID)
                        if (countryId !=null) {
                            SelectCategoryComponent(navController = navController, countryId = countryId)
                        }
                    }

                    composable(Screens.QUESTIONS + "/{${Arguments.COUNTRY_ID}}/{${Arguments.CATEGORY_ID}}", arguments = listOf(
                        navArgument(Arguments.COUNTRY_ID) {
                            type = NavType.StringType
                        }, navArgument(Arguments.CATEGORY_ID){
                            type = NavType.StringType
                        }
                    )){
                        val countryId = it.arguments?.getString(Arguments.COUNTRY_ID)
                        val categoryId = it.arguments?.getString(Arguments.CATEGORY_ID)

                        if (categoryId !=null && countryId !=null){
                            QuestionParent(navController = navController, categoryId = categoryId, countryId = countryId)
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        mainActivityViewModel.getCategories()
        mainActivityViewModel.getCountries()
        mainActivityViewModel.getQuestions()
        super.onStart()

    }


    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->

            val unconfirmed: List<String> = result.filterValues {
                !it
            }.keys.toList()

            println(unconfirmed)
            if (unconfirmed.isNotEmpty()) {
                this@MainActivity.hasPermissions(this@MainActivity, *permissions)
            }


        }


    private fun checkPermissions(permissions: Array<out String>) {
        if (!hasPermissions(this, *permissions)) {
            requestPermissions.launch(permissions)
        } else {
            hasPremissions = true
        }
    }


    private fun hasPermissions(context: Context, vararg permissions: String): Boolean =
        permissions.all {
            ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }


}

