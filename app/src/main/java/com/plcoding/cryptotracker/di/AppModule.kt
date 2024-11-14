package com.plcoding.cryptotracker.di

import com.plcoding.cryptotracker.core.data.networking.HttpClientFactory
import com.plcoding.cryptotracker.crypto.data.networking.RemoteCoinDataSource
import com.plcoding.cryptotracker.crypto.domain.CoinDataSource
import com.plcoding.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    //The bind function tells Koin that when we request a CoinDataSource abstraction then inject the RemoteCoinDataSource implementation
    single { RemoteCoinDataSource(get()) }.bind<CoinDataSource>()
//    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>() //Alternate way to to do the above
    viewModelOf(::CoinListViewModel)
}