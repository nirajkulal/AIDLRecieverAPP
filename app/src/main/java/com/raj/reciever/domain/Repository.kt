package com.raj.reciever.domain

import android.content.Context

interface Repository {
    fun connectService(context: Context)
    fun disconnectService(context: Context)
    fun getMessage(onMessageReceived: (String) -> Unit)
}