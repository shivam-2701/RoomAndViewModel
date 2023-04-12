package com.example.roomandviewmodel

import android.os.Build
import androidx.annotation.RequiresApi

object Constants {
    @RequiresApi(Build.VERSION_CODES.S)
    private val packageName="com.example.roomandviewmodel"
     val EXTRA_TITLE="$packageName.EXTRA_TITLE"
     val EXTRA_DESCRIPTION="$packageName.EXTRA_DESCRIPTION"
     val EXTRA_PRIORITY="$packageName.EXTRA_PRIORITY"
}