package com.raj.reciever.domain.usecase

import android.content.Context
import com.raj.reciever.domain.Repository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SeviceUsecases  @Inject constructor(
    val repository: Repository,
    @ApplicationContext private val context: Context
)  {
    fun connectService() {
        repository.connectService(context)
    }

    fun disconnectService() {
        repository.disconnectService(context)
    }
}