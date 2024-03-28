package com.example.novaweather.utils

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity

object PermissionUtils {

    fun checkPermissions(context: Context, vararg permissions: String): Boolean {
        val grantList = mutableListOf<Boolean>()
        permissions.forEach {
            grantList.add(ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED)
        }
        return !grantList.contains(false)
    }

    fun checkPermissions(context: Context, permissions: List<String>): Boolean {
        val grantList = mutableListOf<Boolean>()
        permissions.forEach {
            grantList.add(ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED)
        }
        return !grantList.contains(false)
    }

    fun requestPermissions(activity: FragmentActivity, vararg permissions: String, onGranted: () -> Unit) {
        Log.d("HomeFragment", "requestPermissions: $permissions")

        val request = activity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { grantedMap ->
            if(!grantedMap.values.contains(false)) onGranted()
        }
        request.launch(
            permissions.toList().toTypedArray()
        )
    }
}