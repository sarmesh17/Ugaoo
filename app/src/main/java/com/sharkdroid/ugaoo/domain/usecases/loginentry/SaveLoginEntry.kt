package com.sharkdroid.ugaoo.domain.usecases.loginentry

import com.sharkdroid.ugaoo.domain.manager.LocalUserManager

class SaveLoginEntry(private  val userManager: LocalUserManager) {

    suspend operator fun invoke (){

        userManager.saveLoginEntry()

    }

}