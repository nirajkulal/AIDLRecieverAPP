package com.raj.reciever.application.vm

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

import com.raj.mylibrary.MviEffect
import com.raj.mylibrary.MviEvent
import com.raj.mylibrary.MviState
import com.raj.mylibrary.MviViewModel
import com.raj.reciever.domain.usecase.MessageUseCase
import com.raj.reciever.domain.usecase.SeviceUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecieverVM @Inject constructor(
    val useCases: MessageUseCase,
    val serviceUsecases: SeviceUsecases
) : MviViewModel<ViewState, ViewEvents, ViewEffect>(
    ViewState(

    )
) {
    override suspend fun handleEffects(effect: ViewEffect) {

    }

    override suspend fun handleEvents(event: ViewEvents) {
        when (event) {
            ViewEvents.Init -> {
                serviceUsecases.connectService()
                useCases.receiveMessage {
                    Log.i("MessageService", "receiveMessage"+it)

                    setEvent(ViewEvents.updateMessage(it))
                }
            }
            ViewEvents.Stop -> serviceUsecases.disconnectService()
            is ViewEvents.updateMessage -> {
                updateState {
                    copy(
                        message = mutableStateOf(event.message)
                    )
                }
            }
        }
    }
}
data class ViewState(
    var message: MutableState<String?>? = mutableStateOf("")
) : MviState

sealed class ViewEvents : MviEvent {
    object Init : ViewEvents()
    object Stop : ViewEvents()
    data class updateMessage(var message: String?) : ViewEvents()
}

sealed class ViewEffect : MviEffect