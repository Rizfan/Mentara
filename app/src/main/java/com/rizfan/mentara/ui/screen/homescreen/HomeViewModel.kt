package com.rizfan.mentara.ui.screen.homescreen

import androidx.lifecycle.ViewModel
import com.rizfan.mentara.data.repository.MentaraRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MentaraRepository
) : ViewModel(){

}