package com.raj.reciever.data.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.raj.aidlsender.service.ICallback
import com.raj.aidlsender.service.IMessageReciver

class ServiceManger {
    var messageReciver: IMessageReciver? = null
    var mOnMessageReceived: (String) -> Unit = {}

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(
            p0: ComponentName?, binder: IBinder?
        ) {
            Log.i("MessageService", "onServiceConnected")
            messageReciver = IMessageReciver.Stub.asInterface(binder)
            Log.i("MessageService", "onServiceConnected messageReciver$messageReciver")
            messageReciver?.setMesssageReciever(object : ICallback.Stub() {
                override fun onMesssage(message: String?) {
                    Log.i("MessageService", "onMesssage = " + message)
                    message?.let { mOnMessageReceived.invoke(it) }
                }
            })

        }

        override fun onServiceDisconnected(p0: ComponentName?) {
        }

    }

    fun connectService(context: Context) {
        val intent = Intent().apply {
            setClassName(
                "com.raj.aidlsender", // Package name of the target app
                "com.raj.aidlsender.data.service.MessageService" // Full path of the service class
            )
            action = "com.raj.aidlsender.service.SetCallback"
        }
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    fun disconnectService() {
        // Implement the logic to disconnect from the service
    }

    fun getMessage(onMessageReceived: (String) -> Unit) {
        mOnMessageReceived = onMessageReceived
    }

}