package com.raj.reciever.data.respository

import android.content.Context
import com.raj.reciever.data.service.ServiceManger
import com.raj.reciever.domain.Repository
import dagger.hilt.android.qualifiers.ApplicationContext

class RepositoryImp(
    val serviceManger: ServiceManger,
) : Repository {
    override fun connectService(context: Context) {
        serviceManger.connectService(context)
    }

    override fun disconnectService(context: Context) {
        serviceManger.disconnectService()
    }


    override fun getMessage(onMessageReceived: (String) -> Unit) {
        serviceManger.getMessage(onMessageReceived)
    }
}