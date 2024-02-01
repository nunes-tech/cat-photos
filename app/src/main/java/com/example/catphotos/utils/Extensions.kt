package com.example.catphotos.utils

import android.app.Activity
import android.widget.Toast


object Extensions {

    fun Activity.message(message:String) {
        return Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}