package com.sharkdroid.ugaoo.domain.usecases.splashrecord

import com.sharkdroid.ugaoo.domain.manager.LocalUserManager

class SaveSplashRecord(private val userManager: LocalUserManager) {

  suspend  operator fun invoke (){

        userManager.saveSplashRecord()
    }
}