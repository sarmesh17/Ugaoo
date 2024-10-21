package com.sharkdroid.ugaoo.domain.usecases.splashrecord

import com.sharkdroid.ugaoo.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadSplashEntry(private  val userManager: LocalUserManager) {

    operator fun invoke ():Flow<Boolean>{

        return userManager.readSplashEntry()

    }
}