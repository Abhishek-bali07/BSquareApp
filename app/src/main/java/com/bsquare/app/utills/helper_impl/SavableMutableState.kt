package com.bsquare.app.utills.helper_impl

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle

class SavableMutableState<T>(
    private val key: UiData,
    private val savedStateHandle: SavedStateHandle,
    initialData: T,
) {
    private val _composeState = mutableStateOf(savedStateHandle[key.name] ?: initialData)
    val value get() = _composeState.value

    fun setValue(data: T) {
        _composeState.value = data
        savedStateHandle[key.name] = data
    }

    fun reset(resetData: T) {
        _composeState.value = resetData
        savedStateHandle.remove<T>(key.name)
    }
}