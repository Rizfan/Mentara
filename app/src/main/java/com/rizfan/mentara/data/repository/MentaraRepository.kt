package com.rizfan.mentara.data.repository

import com.rizfan.mentara.data.pref.UserPreference
import javax.inject.Inject

class MentaraRepository @Inject constructor(
    private val userPreference : UserPreference
){

}