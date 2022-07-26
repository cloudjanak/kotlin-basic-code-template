package com.zw.template.di.modules

import com.zw.template.repositories.LoginRepository
import com.zw.template.viewmodels.LoginViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun provideLoginViewModel(loginRepository: LoginRepository): LoginViewModel {
        return LoginViewModel(loginRepository)
    }
}