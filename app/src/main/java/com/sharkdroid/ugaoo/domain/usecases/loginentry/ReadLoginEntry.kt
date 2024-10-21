package com.sharkdroid.ugaoo.domain.usecases.loginentry

import com.sharkdroid.ugaoo.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadLoginEntry(private val userManager: LocalUserManager) {

    operator  fun invoke():Flow<Boolean>{

        return userManager.readLoginEntry()

    }

}