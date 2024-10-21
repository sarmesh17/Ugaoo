package com.sharkdroid.ugaoo.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.sharkdroid.ugaoo.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private  val Context.dataStore:DataStore<Preferences> by preferencesDataStore("User_Setting")
class LocalUserManagerImp( private val context: Context):LocalUserManager {
    override  fun readLoginEntry(): Flow<Boolean> {

        return context.dataStore.data.map{
            it[PreferenceKey.LOGIN_ENTRY]?: false

        }

    }

    override suspend fun saveLoginEntry() {
        context.dataStore.edit {
            it[PreferenceKey.LOGIN_ENTRY] = true
        }
    }

    override fun readSplashEntry(): Flow<Boolean> {

        return context.dataStore.data.map {
            it[PreferenceKey.SplashRecord]?: false
        }

    }

    override suspend fun saveSplashRecord() {

        context.dataStore.edit {
            it[PreferenceKey.SplashRecord]= true
        }
    }


}

private object PreferenceKey{

    val LOGIN_ENTRY = booleanPreferencesKey(name = "Login Entry")

    val SplashRecord = booleanPreferencesKey(name = "Splash Record")
}