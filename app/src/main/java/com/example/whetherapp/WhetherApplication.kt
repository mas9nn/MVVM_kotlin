package com.example.whetherapp

import android.app.Application
import com.example.whetherapp.data.db.AppDatabase
import com.example.whetherapp.data.network.MyApi
import com.example.whetherapp.data.network.NetworkConnectionInterceptor
import com.example.whetherapp.data.repositories.QuoteRepository
import com.example.whetherapp.data.repositories.UserRepository
import com.example.whetherapp.ui.auth.AuthViewModelFactory
import com.example.whetherapp.ui.home.profile.ProfileViewModelFactory
import com.example.whetherapp.ui.home.quates.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class WhetherApplication :Application(),KodeinAware{

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@WhetherApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance())}
        bind() from singleton { MyApi(instance())}
        bind() from  singleton { AppDatabase(instance())}

        bind() from singleton { UserRepository(instance(),instance())}
        bind() from singleton { QuoteRepository(instance(),instance())}
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }
    }

}