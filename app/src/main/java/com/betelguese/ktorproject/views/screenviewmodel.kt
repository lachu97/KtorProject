package com.betelguese.ktorproject.views

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betelguese.ktorproject.commons.Resource
import com.betelguese.ktorproject.domain.commentstate
import com.betelguese.ktorproject.domain.repos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class myViewModel @Inject constructor(
    private val repos: repos
) : ViewModel() {
    private val _myvalue = mutableStateOf(commentstate())
    val newvalue: State<commentstate> = _myvalue

    init {
        getComments()
    }

     fun getComments() {
        repos().onEach {
            when (it) {
                is Resource.Loading -> {
                    _myvalue.value = commentstate(isloading = true)
                }
                is Resource.Error -> {
                    _myvalue.value = commentstate(error = it.message ?: "Error Occurred")
                }
                is Resource.Success -> {
                    _myvalue.value = commentstate(comment = it.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}