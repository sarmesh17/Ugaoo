package com.sharkdroid.ugaoo.domain.usecases

import com.sharkdroid.ugaoo.domain.usecases.loginentry.ReadLoginEntry
import com.sharkdroid.ugaoo.domain.usecases.loginentry.SaveLoginEntry
import com.sharkdroid.ugaoo.domain.usecases.splashrecord.ReadSplashEntry
import com.sharkdroid.ugaoo.domain.usecases.splashrecord.SaveSplashRecord

data class AllUseCases(
     val readLoginEntry: ReadLoginEntry,
    val saveLoginEntry: SaveLoginEntry,
    val readSplashEntry: ReadSplashEntry,
    val saveSplashRecord: SaveSplashRecord
 )