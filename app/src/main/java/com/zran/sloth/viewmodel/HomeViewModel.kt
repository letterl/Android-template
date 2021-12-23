package com.zran.sloth.viewmodel

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.zran.sloth.entity.NoteContract
import com.zran.sloth.entity.NoteItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeViewModel :
    BaseViewModel<NoteContract.State, NoteContract.Event, NoteContract.Effect>() {
//    private val noteList: MutableList<NoteItem> = mutableListOf(NoteItem("1"))
    @OptIn(ExperimentalMaterial3Api::class)
    private val drawerState = mutableStateOf(DrawerValue.Closed)

    override fun createInitialState(): NoteContract.State {
        //init item list

        val state = NoteContract.State(
            pageTitle = "首页",
            refreshStatus = NoteContract.RefreshStatus.RefreshInit,
            noteList = mutableListOf(NoteItem("1"))
        )
        return MutableStateFlow(state).value
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun getDwState(): DrawerValue {
        return drawerState.value
    }





    fun showToast(text: String){
        setEffect {
            NoteContract.Effect.ShowErrorToastEffect(text = text)
        }
    }

    fun setState(){

    }



    override fun handleEvent(event: NoteContract.Event) {

    }
}