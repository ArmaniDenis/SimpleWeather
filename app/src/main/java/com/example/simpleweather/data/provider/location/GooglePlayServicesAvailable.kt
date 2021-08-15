package com.example.simpleweather.data.provider.location

import android.app.Activity
import android.app.Dialog
import android.content.Context
import com.example.simpleweather.internal.Constants
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

class GooglePlayServicesAvailable constructor(private val context: Context) {


    sealed class Result {
        object True : Result()
        object False : Result()
        class FalseDialog(val dialog: Dialog) : Result()
    }

    private val api = GoogleApiAvailability.getInstance()

    fun isAvailableShowDialog(activity: Activity): Result {
        api.makeGooglePlayServicesAvailable(activity)
        val code = api.isGooglePlayServicesAvailable(context)
        if (code == ConnectionResult.SUCCESS) return Result.True
        if (api.isUserResolvableError(code)) return Result.FalseDialog(
            api.getErrorDialog(activity, code, Constants.PLAY_SERVICES_RESOLUTION_REQUEST)
        )
        return Result.False
    }

}