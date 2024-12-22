package com.raj.reciever.domain.usecase

import com.raj.reciever.domain.Repository
import javax.inject.Inject

class MessageUseCase @Inject constructor(
    val repository: Repository
) {
    fun receiveMessage(onMessageReceived: (String) -> Unit) {
        repository.getMessage {
            onMessageReceived(it)
        }
    }
}