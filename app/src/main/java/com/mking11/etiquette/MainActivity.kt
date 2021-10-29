package com.mking11.etiquette

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.mking11.etiquette.ui.theme.MyApplicationTheme
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            checkPermissions(permissions)
            MyApplicationTheme {

                val navController = rememberNavController()


                Nav

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
                this@MainActivity.hasPermissions (this@MainActivity,*permissions)
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

