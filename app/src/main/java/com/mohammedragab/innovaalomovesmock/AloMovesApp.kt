package com.mohammedragab.innovaalomovesmock

import android.app.Application
import android.content.Context
import com.mohammedragab.innovaalomovesmock.di.aloMoveViewModel
import com.mohammedragab.innovaalomovesmock.di.networkModule
import com.mohammedragab.innovaalomovesmock.di.repositoryModule
import com.mohammedragab.innovaalomovesmock.di.useCaseModule
import org.koin.core.context.GlobalContext

class AloMovesApp: Application() {
    private var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        context = this
        startKoinDI()
    }



    private fun provideComponent() = appComponent

    private fun startKoinDI() {
        GlobalContext.startKoin {
            modules(
                provideComponent()
            )
        }

    }

    private val appComponent = listOf(networkModule, repositoryModule,useCaseModule,aloMoveViewModel)

}