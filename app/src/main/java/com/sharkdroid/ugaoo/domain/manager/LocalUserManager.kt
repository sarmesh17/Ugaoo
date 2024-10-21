package com.sharkdroid.ugaoo.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

  fun readLoginEntry():Flow<Boolean>

    suspend fun saveLoginEntry()

    fun readSplashEntry():Flow<Boolean>

    suspend fun saveSplashRecord()

}