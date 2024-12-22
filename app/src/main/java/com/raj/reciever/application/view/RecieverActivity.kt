package com.raj.reciever.application.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import com.raj.reciever.application.vm.RecieverVM
import com.raj.reciever.application.vm.ViewEvents
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class RecieverActivity : ComponentActivity() {
    private val viewModel: RecieverVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AIDLRecieverPage(viewModel)
        }
    }


    override fun onStart() {
        super.onStart()
        viewModel.setEvent(ViewEvents.Init)
    }

    override fun onStop() {
        super.onStop()
        viewModel.setEvent(ViewEvents.Stop)

    }
}

@Composable
private fun AIDLRecieverPage(vM: RecieverVM) {
    val message :MutableState<String?>?  = vM.state.collectAsState().value.message
    Log.i("MessageService", "AIDLRecieverPage"+message)
    Column {
        Text(
            text ="From service:"
        )
        Text(
            text = message?.value ?: "No Message"
        )
    }


}


