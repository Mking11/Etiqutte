package com.mking11.etiquette

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.mking11.etiquette.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val requestCodeStorageReadPermission = 1003

    private var hasPremissions = false

    private val permissions = arrayOf(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.INTERNET,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            checkPermissions()
            MyApplicationTheme {

                val navController = rememberNavController()

            }
        }
    }

    private fun checkPermissions() {
        if (!hasPermissions(this, *permissions)) {
            ActivityCompat.requestPermissions(this, permissions, requestCodeStorageReadPermission)
        } else {
            hasPremissions = true
        }
    }

    fun hasPermissions(context: Context, vararg permissions: String): Boolean =
        permissions.all {
            ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }


    //request permission result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1003) {
            if (grantResults.isEmpty() || grantResults.contains(PackageManager.PERMISSION_DENIED)) {
                checkPermissions()
            }
        }


    }
}

