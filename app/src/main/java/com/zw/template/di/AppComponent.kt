package com.zw.template.di

import com.zw.template.activities.LoginActivity
import com.zw.template.di.modules.ContextModule
import com.zw.template.di.modules.NetworkModule
import com.zw.template.di.modules.RepositoryModule
import com.zw.template.di.modules.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ContextModule::class, ViewModelModule::class, RepositoryModule::class, NetworkModule::class]
)
interface AppComponent {
    // Classes that can be injected by this Component
    fun inject(application: ZwApplication)
    fun inject(activity: LoginActivity)
}