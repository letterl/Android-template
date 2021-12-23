package com.zran.sloth.entity

import com.zran.sloth.viewmodel.UiEffect
import com.zran.sloth.viewmodel.UiEvent
import com.zran.sloth.viewmodel.UiState


class NoteContract {

    data class State(
        val pageTitle: String,
        val refreshStatus: RefreshStatus,
        val noteList: MutableList<NoteItem>
    ) : UiState

    sealed class Event : UiEvent {
        // 下拉刷新事件
        object RefreshNoteListEvent : Event()

        // 上拉加载事件
        object LoadMoreNoteListEvent: Event()

        // 添加按键点击事件
        object AddingButtonClickEvent : Event()

        // 列表item点击事件
        data class ListItemClickEvent(val item: NoteItem) : Event()

        // 添加项弹窗消失事件
        object AddingNoteDialogDismiss : Event()

        // 添加项弹窗添加确认点击事件
        data class AddingNoteDialogConfirm(val title: String, val desc: String) : Event()

        // 添加项弹窗取消确认点击事件
        object AddingNoteDialogCanceled : Event()
    }

    sealed class Effect : UiEffect {

        // 弹出数据加载错误Toast
        data class ShowErrorToastEffect(val text: String) : Effect()

        // 弹出添加项弹窗
        object ShowAddNoteDialog : Effect()
    }

    sealed class LoadStatus {

        object LoadMoreInit : LoadStatus()

        object LoadMoreLoading : LoadStatus()

        data class LoadMoreSuccess(val hasMore: Boolean) : LoadStatus()

        data class LoadMoreError(val exception: Throwable) : LoadStatus()

        data class LoadMoreFailed(val errCode: Int) : LoadStatus()

    }

    sealed class RefreshStatus {

        object RefreshInit : RefreshStatus()

        object RefreshLoading : RefreshStatus()

        data class RefreshSuccess(val hasMore: Boolean) : RefreshStatus()

        data class RefreshError(val exception: Throwable) : RefreshStatus()

        data class RefreshFailed(val errCode: Int) : RefreshStatus()

    }
}