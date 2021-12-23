package com.zran.sloth.ui.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zran.sloth.viewmodel.HomeViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@OptIn(InternalCoroutinesApi::class)
@Composable
fun HandelRoute() {
//    var vm: HomeViewModel = viewModel()

//    vm.viewModelScope.coroutineContext.let {
//        vm.viewModelScope.launch {
//            vm.effect.collect {
//                when (it) {
//
//                    else -> {
//                    }
//                }
//            }
//        }
//    }
}