package com.example.parsing.di

import androidx.lifecycle.ViewModel
import com.example.parsing.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface ViewModelModule {

    @IntoMap
    @StringKey("MainViewModel")
    @Binds
    fun bindMainViewModel(mainViewModel: MainViewModel) : ViewModel
}