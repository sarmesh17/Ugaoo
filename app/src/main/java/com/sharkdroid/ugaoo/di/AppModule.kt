package com.sharkdroid.ugaoo.di

import android.content.Context
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.sharkdroid.ugaoo.data.LocalUserManagerImp
import com.sharkdroid.ugaoo.domain.manager.LocalUserManager
import com.sharkdroid.ugaoo.domain.usecases.AllUseCases
import com.sharkdroid.ugaoo.domain.usecases.loginentry.ReadLoginEntry
import com.sharkdroid.ugaoo.domain.usecases.loginentry.SaveLoginEntry
import com.sharkdroid.ugaoo.domain.usecases.splashrecord.ReadSplashEntry
import com.sharkdroid.ugaoo.domain.usecases.splashrecord.SaveSplashRecord
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseDatabaseReference(): FirebaseDatabase {

        return FirebaseDatabase.getInstance()
    }


    @Provides
    @Singleton

    fun provideFirebaseStorage(): FirebaseStorage {

        return FirebaseStorage.getInstance()

    }

    @Provides
    @Singleton
    fun provideSignInClient(@ApplicationContext context: Context): SignInClient {
        return Identity.getSignInClient(context)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {

        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton

    fun provideLocalUserManager(@ApplicationContext context: Context):LocalUserManager{

        return LocalUserManagerImp(context)

    }

    @Provides
    @Singleton
    fun provideAllUseCase(localUserManager: LocalUserManager): AllUseCases {

        return AllUseCases(
            readLoginEntry = ReadLoginEntry(localUserManager),
            saveLoginEntry = SaveLoginEntry(localUserManager),
            readSplashEntry = ReadSplashEntry(localUserManager),
            saveSplashRecord = SaveSplashRecord(localUserManager)
        )
    }


}