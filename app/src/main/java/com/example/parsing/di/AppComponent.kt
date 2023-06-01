package com.example.parsing.di

import android.content.Context
import com.example.parsing.ParsingApplication
import com.example.parsing.activity.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(parsingApplication: ParsingApplication)

    @Component.Builder
    interface AppComponentBuilder {

        @BindsInstance
        fun context(context: Context): AppComponentBuilder

        fun build(): AppComponent
    }
}